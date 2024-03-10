package br.edu.utfpr.profissional;

import br.edu.utfpr.crud.CrudService;
import br.edu.utfpr.endereco.EnderecoDTO;
import br.edu.utfpr.endereco.EnderecoModel;
import br.edu.utfpr.endereco.EnderecoRepository;
import br.edu.utfpr.enums.Funcao;
import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.especialidade.EspecialidadeModel;
import br.edu.utfpr.especialidade.EspecialidadeRepository;
import br.edu.utfpr.utils.Copy;
import br.edu.utfpr.utils.PageDTO;
import br.edu.utfpr.utils.ResponseUtils;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@ApplicationScoped
public class ProfissionalService implements CrudService<ProfissionalDTO> {

    private ProfissionalRepository repository;
    private EnderecoRepository enderecoRepository;
    private EspecialidadeRepository especialidadeRepository;
    private Validator validator;
    private static final String MENSAGEMNAOENCONTRADA = "Especialidade não encontrada.";

    @Inject
    public ProfissionalService(ProfissionalRepository repository, EnderecoRepository enderecoRepository, EspecialidadeRepository especialidadeRepository, Validator validator){
        this.repository = repository;
        this.enderecoRepository = enderecoRepository;
        this.especialidadeRepository = especialidadeRepository;
        this.validator = validator;
    }

    public Response getAll(){
        List<ProfissionalModel> lista = repository.listAll(Sort.by("id"));
        if(lista.isEmpty()){
            return ResponseUtils.notFound();
        }
        return ResponseUtils.okListaModel(lista);
    }

    public Response findById(long id){
        ProfissionalModel model = repository.findById(id);
        if(model != null){
            return ResponseUtils.okModel(model);
        }

        return ResponseUtils.notFound();
    }

    public Response add(ProfissionalDTO profissionalDTO){
        Set<ConstraintViolation<ProfissionalDTO>> violations = validator.validate(profissionalDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        ProfissionalModel model = new ProfissionalModel();
        model.setNome(profissionalDTO.getNome());
        model.setIdade(profissionalDTO.getIdade());
        model.setCpf(profissionalDTO.getCpf());
        model.setTelefone(profissionalDTO.getTelefone());
        model.setEmail(profissionalDTO.getEmail());
        model.setDataAdmissao(profissionalDTO.getDataAdmissao());
        model.setSalario(profissionalDTO.getSalario());
        model.setSituacao(profissionalDTO.getSituacao());
        model.setFuncao(profissionalDTO.getFuncao());
        if(profissionalDTO.getEspecialidade() != null) {
            EspecialidadeModel especialidadeModel;
            if (especialidadeRepository.findById(profissionalDTO.getEspecialidade()) != null) {
                especialidadeModel = especialidadeRepository.findById(profissionalDTO.getEspecialidade());
            } else {
                return ResponseUtils.notFoundComMotivo(MENSAGEMNAOENCONTRADA);
            }
            model.setEspecialidade(especialidadeModel);
        }else if(Funcao.MEDICO.equals(profissionalDTO.getFuncao())){
            return ResponseUtils.notFoundComMotivo(MENSAGEMNAOENCONTRADA);
        }
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        if(!Copy.copyProperties(enderecoDTO, profissionalDTO.getEndereco())){
            return ResponseUtils.porCodigo(418);
        }

        Set<ConstraintViolation<EnderecoDTO>> violationsEndereco = validator.validate(enderecoDTO);
        if(!violationsEndereco.isEmpty()){
            return ResponseError.createFromViolations(violationsEndereco).returnWithStatusCode(422);
        }
        EnderecoModel enderecoModel = new EnderecoModel();
        if(!Copy.copyProperties(enderecoModel, enderecoDTO)){
            return ResponseUtils.porCodigo(418);
        }

        try{
            enderecoRepository.persist(enderecoModel);
        }catch (Exception ex){
            return ResponseUtils.serverError();
        }

        model.setEndereco(enderecoModel);

        try{
            repository.persist(model);
        }catch (Exception ex){
            return ResponseUtils.serverError();
        }

        return ResponseUtils.criado(model.getId());
    }

    public Response update(long id, ProfissionalDTO profissionalDTO){
        Set<ConstraintViolation<ProfissionalDTO>> violations = validator.validate(profissionalDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        ProfissionalModel model = repository.findById(id);
        if(model != null){
            model.setNome(profissionalDTO.getNome());
            model.setIdade(profissionalDTO.getIdade());
            model.setCpf(profissionalDTO.getCpf());
            model.setTelefone(profissionalDTO.getTelefone());
            model.setEmail(profissionalDTO.getEmail());
            model.setSituacao(profissionalDTO.getSituacao());
            model.setDataAdmissao(profissionalDTO.getDataAdmissao());
            model.setSalario(profissionalDTO.getSalario());
            model.setSituacao(profissionalDTO.getSituacao());
            model.setFuncao(profissionalDTO.getFuncao());

            if(profissionalDTO.getEspecialidade() != null) {
                EspecialidadeModel especialidadeModel;
                if (especialidadeRepository.findById(profissionalDTO.getEspecialidade()) != null) {
                    especialidadeModel = especialidadeRepository.findById(profissionalDTO.getEspecialidade());
                } else {
                    return ResponseUtils.notFoundComMotivo(MENSAGEMNAOENCONTRADA);
                }
                model.setEspecialidade(especialidadeModel);
            }else if(Funcao.MEDICO.equals(profissionalDTO.getFuncao())){
                return ResponseUtils.notFoundComMotivo(MENSAGEMNAOENCONTRADA);
            }
            EnderecoDTO enderecoDTO = new EnderecoDTO();
            if(!Copy.copyProperties(enderecoDTO, profissionalDTO.getEndereco())){
                return ResponseUtils.porCodigo(418);
            }

            Set<ConstraintViolation<EnderecoDTO>> violationsEndereco = validator.validate(enderecoDTO);
            if(!violationsEndereco.isEmpty()){
                return ResponseError.createFromViolations(violationsEndereco).returnWithStatusCode(422);
            }
            EnderecoModel enderecoModel;
            if(enderecoRepository.findById(profissionalDTO.getEndereco().getId()) != null){
                enderecoModel = enderecoRepository.findById(profissionalDTO.getEndereco().getId());
            }else{
                enderecoModel = new EnderecoModel();
            }

            if(!Copy.copyProperties(enderecoModel, profissionalDTO.getEndereco())){
                return ResponseUtils.porCodigo(418);
            }

            try{
                enderecoRepository.persist(enderecoModel);
            }catch (Exception ex){
                return ResponseUtils.serverError();
            }

            model.setEndereco(enderecoModel);

            try{
                repository.persist(model);
            }catch (Exception ex){
                return ResponseUtils.serverError();
            }

            return ResponseUtils.atualizadoPorCodigo(model.getId());
        }

        return ResponseUtils.notFound();
    }

    public Response delete(long id){
        ProfissionalModel model = repository.findById(id);
        if(model != null){
            EnderecoModel enderecoModel = enderecoRepository.findById(model.getEndereco().getId());
            repository.delete(model);
            enderecoRepository.delete(enderecoModel);
            return ResponseUtils.okModel(model);
        }

        return ResponseUtils.notFound();
    }

    public Response page(int page, int size){
        if(page < 0 || size < 1){
            return ResponseUtils.porCodigo(422);
        }
        List<ProfissionalModel> lista = repository.pageList(page,size);
        if(Objects.isNull(lista)){
            return ResponseUtils.notFound();
        }
        PageDTO<ProfissionalModel> pageDTO = new PageDTO<>();
        pageDTO.setLista(lista);
        pageDTO.setPages(repository.pageCount(page,size));
        pageDTO.setTotal(repository.pageTotal(page,size));
        return ResponseUtils.okPage(pageDTO);
    }

    public Response pageSort(int page, int size, String atributo, boolean asc){
        if(page < 0 || size < 1){
            return ResponseUtils.porCodigo(422);
        }
        List<ProfissionalModel> lista = repository.pageListSort(page,size,atributo,asc ? Sort.Direction.Ascending : Sort.Direction.Descending);
        if(Objects.isNull(lista)){
            return ResponseUtils.notFound();
        }
        PageDTO<ProfissionalModel> pageDTO = new PageDTO<>();
        pageDTO.setLista(lista);
        pageDTO.setPages(repository.pageCount(page,size));
        pageDTO.setTotal(repository.pageTotal(page,size));
        return ResponseUtils.okPage(pageDTO);
    }

    public List<String> buscarEmails(){
       return repository.findEmailByFuncaoCuidador();
    }

    public List<String> buscarTelefones(){
        return repository.findTelefoneByFuncaoCuidador();
    }

}
