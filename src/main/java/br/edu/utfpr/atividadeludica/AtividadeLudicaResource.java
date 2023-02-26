package br.edu.utfpr.atividadeludica;

import br.edu.utfpr.erro.ResponseError;
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

@Path("/atividadeludica")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Atividade Lúdica")
public class AtividadeLudicaResource {

    private AtividadeLudicaRepository repository;
    private Validator validator;

    @Inject
    public AtividadeLudicaResource(AtividadeLudicaRepository repository, Validator validator){
        this.repository = repository;
        this.validator = validator;
    }

    @Operation(summary = "Retorna todas")
    @GET
    public Response getAll(){
        List<AtividadeLudicaModel> lista = repository.listAll();
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lista).build();
    }

    @GET
    @Path("{id}")
    public Response getAtividadeLudicaById(@PathParam("id") long id){
        AtividadeLudicaModel model = repository.findById(id);
        if(model != null){
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @POST
    @Transactional
    public Response addAtividadeLudica(AtividadeLudicaDTO atividadeLudicaDTO){
        Set<ConstraintViolation<AtividadeLudicaDTO>> violations = validator.validate(atividadeLudicaDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        AtividadeLudicaModel model = new AtividadeLudicaModel();
        model.setNome(atividadeLudicaDTO.getNome());
        model.setData(atividadeLudicaDTO.getData());
        model.setSituacao(atividadeLudicaDTO.getSituacao());
        model.setHora(atividadeLudicaDTO.getHora());

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
    public Response updateAtividadeLudica(@PathParam("id") long id, AtividadeLudicaDTO atividadeLudicaDTO){
        Set<ConstraintViolation<AtividadeLudicaDTO>> violations = validator.validate(atividadeLudicaDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        AtividadeLudicaModel model = repository.findById(id);
        if(model != null){
            model.setNome(atividadeLudicaDTO.getNome());
            model.setData(atividadeLudicaDTO.getData());
            model.setSituacao(atividadeLudicaDTO.getSituacao());
            model.setHora(atividadeLudicaDTO.getHora());

            return Response.status(201, model.toString()).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteAtividadeLudica(@PathParam("id") long id){
        AtividadeLudicaModel model = repository.findById(id);
        if(model != null){
            repository.delete(model);
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
