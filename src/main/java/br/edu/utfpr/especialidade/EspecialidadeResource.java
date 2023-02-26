package br.edu.utfpr.especialidade;

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

@Path("/especialidade")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Especialidade")
public class EspecialidadeResource {

    private EspecialidadeRepository repository;
    private Validator validator;

    @Inject
    public EspecialidadeResource(EspecialidadeRepository repository, Validator validator){
        this.repository = repository;
        this.validator = validator;
    }

    @Operation(summary = "Retorna todas")
    @GET
    public Response getAll(){
        List<EspecialidadeModel> lista = repository.listAll();
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lista).build();
    }

    @GET
    @Path("{id}")
    public Response getEspecialidadeById(@PathParam("id") long id){
        EspecialidadeModel model = repository.findById(id);
        if(model != null){
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @POST
    @Transactional
    public Response addEspecialidade(EspecialidadeDTO especialidadeDTO){
        Set<ConstraintViolation<EspecialidadeDTO>> violations = validator.validate(especialidadeDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        EspecialidadeModel model = new EspecialidadeModel();
        model.setNome(especialidadeDTO.getNome());

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
    public Response updateEspecialidade(@PathParam("id") long id, EspecialidadeDTO especialidadeDTO){
        Set<ConstraintViolation<EspecialidadeDTO>> violations = validator.validate(especialidadeDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        EspecialidadeModel model = repository.findById(id);
        if(model != null){
            model.setNome(especialidadeDTO.getNome());

            return Response.status(201, model.toString()).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteEspecialidade(@PathParam("id") long id){
        EspecialidadeModel model = repository.findById(id);
        if(model != null){
            repository.delete(model);
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
