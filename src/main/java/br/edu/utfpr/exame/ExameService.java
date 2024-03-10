package br.edu.utfpr.exame;

import br.edu.utfpr.atividadesresidente.atividadeexameresidente.AtividadeExameResidenteModel;
import br.edu.utfpr.atividadesresidente.atividadeexameresidente.AtividadeExameResidenteRepository;
import br.edu.utfpr.crud.CrudService;
import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.especialidade.EspecialidadeModel;
import br.edu.utfpr.especialidade.EspecialidadeRepository;
import br.edu.utfpr.profissional.ProfissionalModel;
import br.edu.utfpr.profissional.ProfissionalRepository;
import br.edu.utfpr.residente.ResidenteModel;
import br.edu.utfpr.residente.ResidenteRepository;
import br.edu.utfpr.utils.PageDTO;
import br.edu.utfpr.utils.ResponseUtils;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class ExameService implements CrudService<ExameDTO> {

    private static final String MENSAGEMESPECIALIDADE = "Especialidade não encontrada.";
    private static final String MENSAGEMPROFISSIONAL = "Profissional não encontrado(a).";
    private static final String MENSAGEMRESIDENTE = "Residente não encontrado(a).";
    private ExameRepository repository;
    private ProfissionalRepository profissionalRepository;
    private ResidenteRepository residenteRepository;
    private EspecialidadeRepository especialidadeRepository;
    private Validator validator;
    private AtividadeExameResidenteRepository atividadeExameResidenteRepository;

    @Inject
    public ExameService(ExameRepository repository, ProfissionalRepository profissionalRepository, ResidenteRepository residenteRepository, EspecialidadeRepository especialidadeRepository, Validator validator, AtividadeExameResidenteRepository atividadeExameResidenteRepository){
        this.repository = repository;
        this.profissionalRepository = profissionalRepository;
        this.residenteRepository = residenteRepository;
        this.especialidadeRepository = especialidadeRepository;
        this.validator = validator;
        this.atividadeExameResidenteRepository = atividadeExameResidenteRepository;
    }

    public Response getAll(){
        List<ExameModel> lista = repository.listAll();
        if(lista.isEmpty()){
            return ResponseUtils.notFound();
        }
        return ResponseUtils.okListaModel(lista);
    }

    public Response findById(long id){
        ExameModel model = repository.findById(id);
        if(model != null){
            return ResponseUtils.okModel(model);
        }

        return ResponseUtils.notFound();
    }

    public Response add(ExameDTO exameDTO){
        Set<ConstraintViolation<ExameDTO>> violations = validator.validate(exameDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        ExameModel model = new ExameModel();
        model.setDescricao(exameDTO.getDescricao());
        model.setDataHora(exameDTO.getDataHora());
        model.setLocal(exameDTO.getLocal());
        model.setLaudo(exameDTO.getLaudo());

        EspecialidadeModel especialidadeModel;
        if(especialidadeRepository.findById(exameDTO.getEspecialidade()) != null){
            especialidadeModel = especialidadeRepository.findById(exameDTO.getEspecialidade());
        }else{
            return ResponseUtils.notFoundComMotivo(MENSAGEMESPECIALIDADE);
        }
        model.setEspecialidade(especialidadeModel);

        ProfissionalModel profissionalModel;
        if(profissionalRepository.findById(exameDTO.getProfissional()) != null){
            profissionalModel = profissionalRepository.findById(exameDTO.getProfissional());
        }else{
            return ResponseUtils.notFoundComMotivo(MENSAGEMPROFISSIONAL);
        }
        model.setProfissional(profissionalModel);

        ResidenteModel residenteModel;
        if(residenteRepository.findById(exameDTO.getResidente()) != null){
            residenteModel = residenteRepository.findById(exameDTO.getResidente());
        }else{
            return ResponseUtils.notFoundComMotivo(MENSAGEMRESIDENTE);
        }
        model.setResidente(residenteModel);

        try{
            repository.persist(model);
            GerarAtividadeExame gerarAtividadeExame = new GerarAtividadeExame();
            atividadeExameResidenteRepository.persist(gerarAtividadeExame.gerar(model));
        }catch (Exception ex){
            return ResponseUtils.serverError();
        }

        return ResponseUtils.criado(model.getId());
    }

    public Response update(long id, ExameDTO exameDTO){
        Set<ConstraintViolation<ExameDTO>> violations = validator.validate(exameDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        ExameModel model = repository.findById(id);
        if(model != null){
            model.setDescricao(exameDTO.getDescricao());
            model.setDataHora(exameDTO.getDataHora());
            model.setLocal(exameDTO.getLocal());
            model.setLaudo(exameDTO.getLaudo());

            EspecialidadeModel especialidadeModel;
            if(especialidadeRepository.findById(exameDTO.getEspecialidade()) != null){
                especialidadeModel = especialidadeRepository.findById(exameDTO.getEspecialidade());
            }else{
                return ResponseUtils.notFoundComMotivo(MENSAGEMESPECIALIDADE);
            }
            model.setEspecialidade(especialidadeModel);

            ProfissionalModel profissionalModel;
            if(profissionalRepository.findById(exameDTO.getProfissional()) != null){
                profissionalModel = profissionalRepository.findById(exameDTO.getProfissional());
            }else{
                return ResponseUtils.notFoundComMotivo(MENSAGEMPROFISSIONAL);
            }
            model.setProfissional(profissionalModel);

            ResidenteModel residenteModel;
            if(residenteRepository.findById(exameDTO.getResidente()) != null){
                residenteModel = residenteRepository.findById(exameDTO.getResidente());
            }else{
                return ResponseUtils.notFoundComMotivo(MENSAGEMRESIDENTE);
            }
            model.setResidente(residenteModel);

            try{
                repository.persist(model);
            }catch (Exception ex){
                return ResponseUtils.serverError();
            }

            if(atividadeExameResidenteRepository.findByExameId(model.getId()) != null){
                AtividadeExameResidenteModel atividadeExameResidenteModel = atividadeExameResidenteRepository.findByExameId(model.getId());
                atividadeExameResidenteModel.setDescricao(model.getDescricao());
                atividadeExameResidenteModel.setDataHora(model.getDataHora());
                atividadeExameResidenteModel.setExame(model);

                try{
                    atividadeExameResidenteRepository.persist(atividadeExameResidenteModel);
                }catch (Exception ex){
                    return ResponseUtils.serverError();
                }
            }

            return ResponseUtils.atualizadoPorCodigo(model.getId());
        }

        return ResponseUtils.notFound();
    }

    public Response delete(long id){
        ExameModel model = repository.findById(id);
        if(model != null){
            repository.delete(model);
            return ResponseUtils.okModel(model);
        }

        return ResponseUtils.notFound();
    }

    public Response page(int page, int size){
        if(page < 0 || size < 1){
            return ResponseUtils.porCodigo(422);
        }
        List<ExameModel> lista = repository.pageList(page,size);
        if(lista.isEmpty()){
            return ResponseUtils.notFound();
        }
        PageDTO<ExameModel> pageDTO = new PageDTO<>();
        pageDTO.setLista(lista);
        pageDTO.setPages(repository.pageCount(page,size));
        pageDTO.setTotal(repository.pageTotal(page,size));
        return ResponseUtils.okPage(pageDTO);
    }

    public Response pageSort(int page, int size, String atributo, boolean asc){
        if(page < 0 || size < 1){
            return ResponseUtils.porCodigo(422);
        }
        List<ExameModel> lista = repository.pageListSort(page,size,atributo,asc ? Sort.Direction.Ascending : Sort.Direction.Descending);
        if(lista.isEmpty()){
            return ResponseUtils.notFound();
        }
        PageDTO<ExameModel> pageDTO = new PageDTO<>();
        pageDTO.setLista(lista);
        pageDTO.setPages(repository.pageCount(page,size));
        pageDTO.setTotal(repository.pageTotal(page,size));
        return ResponseUtils.okPage(pageDTO);
    }

}
