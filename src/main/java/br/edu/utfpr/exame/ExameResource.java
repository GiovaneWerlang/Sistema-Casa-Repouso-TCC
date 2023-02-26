package br.edu.utfpr.exame;

import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.especialidade.EspecialidadeModel;
import br.edu.utfpr.especialidade.EspecialidadeRepository;
import br.edu.utfpr.profissional.ProfissionalModel;
import br.edu.utfpr.profissional.ProfissionalRepository;
import br.edu.utfpr.residente.ResidenteModel;
import br.edu.utfpr.residente.ResidenteRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@Path("/exame")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Exame")
public class ExameResource {

    private ExameRepository repository;
    private ProfissionalRepository profissionalRepository;
    private ResidenteRepository residenteRepository;
    private EspecialidadeRepository especialidadeRepository;
    private Validator validator;

    @Inject
    public ExameResource(ExameRepository repository, ProfissionalRepository profissionalRepository, ResidenteRepository residenteRepository, EspecialidadeRepository especialidadeRepository, Validator validator){
        this.repository = repository;
        this.profissionalRepository = profissionalRepository;
        this.residenteRepository = residenteRepository;
        this.especialidadeRepository = especialidadeRepository;
        this.validator = validator;
    }

    @Operation(summary = "Retorna todos")
    @GET
    public Response getAll(){
        List<ExameModel> lista = repository.listAll();
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lista).build();
    }

    @GET
    @Path("{id}")
    public Response getExameById(@PathParam("id") long id){
        ExameModel model = repository.findById(id);
        if(model != null){
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Transactional
    public Response addExame(ExameDTO exameDTO){
        Set<ConstraintViolation<ExameDTO>> violations = validator.validate(exameDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        ExameModel model = new ExameModel();
        model.setNome(exameDTO.getNome());
        model.setDataHora(exameDTO.getDataHora());
        model.setLocal(exameDTO.getLocal());
        model.setLaudo(exameDTO.getLaudo());


        EspecialidadeModel especialidadeModel;
        if(especialidadeRepository.findById(exameDTO.getEspecialidade()) != null){
            especialidadeModel = especialidadeRepository.findById(exameDTO.getEspecialidade());
        }else{
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Especialidade não encontrada.").build();
        }
        model.setEspecialidade(especialidadeModel);

        ProfissionalModel profissionalModel;
        if(profissionalRepository.findById(exameDTO.getProfissional()) != null){
            profissionalModel = profissionalRepository.findById(exameDTO.getProfissional());
        }else{
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Profissional não encontrado(a).").build();
        }
        model.setProfissional(profissionalModel);

        ResidenteModel residenteModel;
        if(residenteRepository.findById(exameDTO.getResidente()) != null){
            residenteModel = residenteRepository.findById(exameDTO.getResidente());
        }else{
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Residente não encontrado(a).").build();
        }
        model.setResidente(residenteModel);

        try{
            repository.persist(model);
        }catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status( Response.Status.CREATED.getStatusCode(),model.toString()).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateExame(@PathParam("id") long id, ExameDTO exameDTO){
        Set<ConstraintViolation<ExameDTO>> violations = validator.validate(exameDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        ExameModel model = repository.findById(id);
        if(model != null){
            model.setNome(exameDTO.getNome());
            model.setDataHora(exameDTO.getDataHora());
            model.setLocal(exameDTO.getLocal());
            model.setLaudo(exameDTO.getLaudo());

            EspecialidadeModel especialidadeModel;
            if(especialidadeRepository.findById(exameDTO.getEspecialidade()) != null){
                especialidadeModel = especialidadeRepository.findById(exameDTO.getEspecialidade());
            }else{
                return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Especialidade não encontrada.").build();
            }
            model.setEspecialidade(especialidadeModel);

            ProfissionalModel profissionalModel;
            if(profissionalRepository.findById(exameDTO.getProfissional()) != null){
                profissionalModel = profissionalRepository.findById(exameDTO.getProfissional());
            }else{
                return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Profissional não encontrado(a).").build();
            }
            model.setProfissional(profissionalModel);

            ResidenteModel residenteModel;
            if(residenteRepository.findById(exameDTO.getResidente()) != null){
                residenteModel = residenteRepository.findById(exameDTO.getResidente());
            }else{
                return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Residente não encontrado(a).").build();
            }
            model.setResidente(residenteModel);

            try{
                repository.persist(model);
            }catch (Exception ex){
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }

            return Response.status(201, model.toString()).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteExame(@PathParam("id") long id){
        ExameModel model = repository.findById(id);
        if(model != null){
            repository.delete(model);
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
