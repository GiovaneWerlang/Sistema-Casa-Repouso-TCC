package br.edu.utfpr.consulta;

import br.edu.utfpr.atividadesresidente.atividadeconsultaresidente.AtividadeConsultaResidenteModel;
import br.edu.utfpr.atividadesresidente.atividadeconsultaresidente.AtividadeConsultaResidenteRepository;
import br.edu.utfpr.crud.CrudService;
import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.especialidade.EspecialidadeModel;
import br.edu.utfpr.especialidade.EspecialidadeRepository;
import br.edu.utfpr.profissional.ProfissionalModel;
import br.edu.utfpr.profissional.ProfissionalRepository;
import br.edu.utfpr.residente.ResidenteModel;
import br.edu.utfpr.residente.ResidenteRepository;
import br.edu.utfpr.utils.ResponseUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Set;

@ApplicationScoped
public class ConsultaService extends CrudService<ConsultaModel, ConsultaDTO, ConsultaRepository> {

    private ConsultaRepository repository;
    private ProfissionalRepository profissionalRepository;
    private ResidenteRepository residenteRepository;
    private EspecialidadeRepository especialidadeRepository;
    private Validator validator;
    private AtividadeConsultaResidenteRepository atividadeConsultaResidenteRepository;

    @Inject
    public ConsultaService(ConsultaRepository repository, ProfissionalRepository profissionalRepository, ResidenteRepository residenteRepository, EspecialidadeRepository especialidadeRepository, Validator validator,AtividadeConsultaResidenteRepository atividadeConsultaResidenteRepository){
        super(repository, validator);
        this.repository = repository;
        this.profissionalRepository = profissionalRepository;
        this.residenteRepository = residenteRepository;
        this.especialidadeRepository = especialidadeRepository;
        this.validator = validator;
        this.atividadeConsultaResidenteRepository = atividadeConsultaResidenteRepository;
    }

    public Response getAll(){
        List<ConsultaModel> lista = repository.listAll();
        if(lista.isEmpty()){
            return ResponseUtils.notFound();
        }
        return ResponseUtils.okListaModel(lista);
    }

    public Response findById(long id){
        ConsultaModel model = repository.findById(id);
        if(model != null){
            return ResponseUtils.okModel(model);
        }

        return ResponseUtils.notFound();
    }

    public Response add(ConsultaDTO consultaDTO){
        Set<ConstraintViolation<ConsultaDTO>> violations = validator.validate(consultaDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        ConsultaModel model = new ConsultaModel();
        model.setDescricao(consultaDTO.getDescricao());
        model.setDataHora(consultaDTO.getDataHora());
        model.setLocal(consultaDTO.getLocal());
        model.setPrescricao(consultaDTO.getPrescricao());


        EspecialidadeModel especialidadeModel;
        if(especialidadeRepository.findById(consultaDTO.getEspecialidade()) != null){
            especialidadeModel = especialidadeRepository.findById(consultaDTO.getEspecialidade());
        }else{
            return ResponseUtils.notFoundComMotivo("Especialidade não encontrada.");
        }
        model.setEspecialidade(especialidadeModel);

        ProfissionalModel profissionalModel;
        if(profissionalRepository.findById(consultaDTO.getProfissional()) != null){
            profissionalModel = profissionalRepository.findById(consultaDTO.getProfissional());
        }else{
            return ResponseUtils.notFoundComMotivo("Profissional não encontrado(a).");
        }
        model.setProfissional(profissionalModel);

        ResidenteModel residenteModel;
        if(residenteRepository.findById(consultaDTO.getResidente()) != null){
            residenteModel = residenteRepository.findById(consultaDTO.getResidente());
        }else{
            return ResponseUtils.notFoundComMotivo("Residente não encontrado(a).");
        }
        model.setResidente(residenteModel);

        try{
            repository.persist(model);
            GerarAtividadeConsulta gerarAtividadeConsulta = new GerarAtividadeConsulta();
            atividadeConsultaResidenteRepository.persist(gerarAtividadeConsulta.gerar(model));
        }catch (Exception ex){
            return ResponseUtils.serverError();
        }

        return ResponseUtils.criado(model.getId());
    }

    public Response update(long id, ConsultaDTO consultaDTO){
        Set<ConstraintViolation<ConsultaDTO>> violations = validator.validate(consultaDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        ConsultaModel model = repository.findById(id);
        if(model != null){
            model.setDescricao(consultaDTO.getDescricao());
            model.setDataHora(consultaDTO.getDataHora());
            model.setLocal(consultaDTO.getLocal());
            model.setPrescricao(consultaDTO.getPrescricao());

            EspecialidadeModel especialidadeModel;
            if(especialidadeRepository.findById(consultaDTO.getEspecialidade()) != null){
                especialidadeModel = especialidadeRepository.findById(consultaDTO.getEspecialidade());
            }else{
                return ResponseUtils.notFoundComMotivo("Especialidade não encontrada.");
            }
            model.setEspecialidade(especialidadeModel);

            ProfissionalModel profissionalModel;
            if(profissionalRepository.findById(consultaDTO.getProfissional()) != null){
                profissionalModel = profissionalRepository.findById(consultaDTO.getProfissional());
            }else{
                return ResponseUtils.notFoundComMotivo("Profissional não encontrado(a).");
            }
            model.setProfissional(profissionalModel);

            ResidenteModel residenteModel;
            if(residenteRepository.findById(consultaDTO.getResidente()) != null){
                residenteModel = residenteRepository.findById(consultaDTO.getResidente());
            }else{
                return ResponseUtils.notFoundComMotivo("Residente não encontrado(a).");
            }
            model.setResidente(residenteModel);

            try{
                repository.persist(model);
            }catch (Exception ex){
                return ResponseUtils.serverError();
            }

            if(atividadeConsultaResidenteRepository.findByConsultaId(model.getId()) != null){
                AtividadeConsultaResidenteModel atividadeConsultaResidenteModel = atividadeConsultaResidenteRepository.findByConsultaId(model.getId());
                atividadeConsultaResidenteModel.setDescricao(model.getDescricao());
                atividadeConsultaResidenteModel.setDataHora(model.getDataHora());
                atividadeConsultaResidenteModel.setConsulta(model);

                try{
                    atividadeConsultaResidenteRepository.persist(atividadeConsultaResidenteModel);
                }catch (Exception ex){
                    return ResponseUtils.serverError();
                }
            }

            return ResponseUtils.atualizadoPorCodigo(model.getId());
        }

        return ResponseUtils.notFound();
    }

    public Response delete(long id){
        ConsultaModel model = repository.findById(id);
        if(model != null){
            repository.delete(model);
            return ResponseUtils.okModel(model);
        }

        return ResponseUtils.notFound();
    }

}
