package br.edu.utfpr.consulta;

import br.edu.utfpr.atividadesresidente.atividadeconsultaresidente.AtividadeConsultaResidenteModel;
import br.edu.utfpr.atividadesresidente.atividadeconsultaresidente.AtividadeConsultaResidenteRepository;
import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.especialidade.EspecialidadeModel;
import br.edu.utfpr.especialidade.EspecialidadeRepository;
import br.edu.utfpr.profissional.ProfissionalModel;
import br.edu.utfpr.profissional.ProfissionalRepository;
import br.edu.utfpr.residente.ResidenteModel;
import br.edu.utfpr.residente.ResidenteRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class ConsultaService {

    private ConsultaRepository repository;
    private ProfissionalRepository profissionalRepository;
    private ResidenteRepository residenteRepository;
    private EspecialidadeRepository especialidadeRepository;
    private Validator validator;
    private AtividadeConsultaResidenteRepository atividadeConsultaResidenteRepository;

    @Inject
    public ConsultaService(ConsultaRepository repository, ProfissionalRepository profissionalRepository, ResidenteRepository residenteRepository, EspecialidadeRepository especialidadeRepository, Validator validator,AtividadeConsultaResidenteRepository atividadeConsultaResidenteRepository){
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
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lista).build();
    }

    public Response findById(long id){
        ConsultaModel model = repository.findById(id);
        if(model != null){
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
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
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Especialidade não encontrada.").build();
        }
        model.setEspecialidade(especialidadeModel);

        ProfissionalModel profissionalModel;
        if(profissionalRepository.findById(consultaDTO.getProfissional()) != null){
            profissionalModel = profissionalRepository.findById(consultaDTO.getProfissional());
        }else{
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Profissional não encontrado(a).").build();
        }
        model.setProfissional(profissionalModel);

        ResidenteModel residenteModel;
        if(residenteRepository.findById(consultaDTO.getResidente()) != null){
            residenteModel = residenteRepository.findById(consultaDTO.getResidente());
        }else{
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Residente não encontrado(a).").build();
        }
        model.setResidente(residenteModel);

        try{
            repository.persist(model);
            AtividadeConsultaResidenteModel atividadeConsultaResidenteModel = new AtividadeConsultaResidenteModel();
            atividadeConsultaResidenteModel.setDescricao(model.getDescricao());
            atividadeConsultaResidenteModel.setDataHora(model.getDataHora());
            atividadeConsultaResidenteModel.setConsulta(model);
            atividadeConsultaResidenteRepository.persist(atividadeConsultaResidenteModel);
        }catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status( Response.Status.CREATED.getStatusCode(),model.toString()).build();
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
                return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Especialidade não encontrada.").build();
            }
            model.setEspecialidade(especialidadeModel);

            ProfissionalModel profissionalModel;
            if(profissionalRepository.findById(consultaDTO.getProfissional()) != null){
                profissionalModel = profissionalRepository.findById(consultaDTO.getProfissional());
            }else{
                return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Profissional não encontrado(a).").build();
            }
            model.setProfissional(profissionalModel);

            ResidenteModel residenteModel;
            if(residenteRepository.findById(consultaDTO.getResidente()) != null){
                residenteModel = residenteRepository.findById(consultaDTO.getResidente());
            }else{
                return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Residente não encontrado(a).").build();
            }
            model.setResidente(residenteModel);

            try{
                repository.persist(model);
            }catch (Exception ex){
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }

            AtividadeConsultaResidenteModel atividadeConsultaResidenteModel = new AtividadeConsultaResidenteModel();
            if(atividadeConsultaResidenteRepository.findByConsultaId(model.getId()) != null){
                atividadeConsultaResidenteModel = atividadeConsultaResidenteRepository.findByConsultaId(model.getId());
                if(atividadeConsultaResidenteModel.getSituacao() == null) {
                    atividadeConsultaResidenteModel.setDescricao(model.getDescricao());
                    atividadeConsultaResidenteModel.setDataHora(model.getDataHora());
                    atividadeConsultaResidenteModel.setConsulta(model);

                    try{
                        atividadeConsultaResidenteRepository.persist(atividadeConsultaResidenteModel);
                    }catch (Exception ex){
                        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                    }
                }
            }

            return Response.status(201, model.toString()).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response delete(long id){
        ConsultaModel model = repository.findById(id);
        if(model != null){
            repository.delete(model);
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
