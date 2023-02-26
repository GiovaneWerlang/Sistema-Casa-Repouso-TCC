package br.edu.utfpr.entradasaida;



import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.residente.ResidenteModel;
import br.edu.utfpr.residente.ResidenteRepository;
import io.quarkus.vertx.http.runtime.devmode.Json;
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

@Path("/entradasaida")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Entrada Saída")
public class EntradaSaidaResource {

    private EntradaSaidaRepository repository;
    private ResidenteRepository residenteRepository;
    private Validator validator;

    @Inject
    public EntradaSaidaResource(EntradaSaidaRepository repository, ResidenteRepository residenteRepository, Validator validator){
        this.repository = repository;
        this.residenteRepository = residenteRepository;
        this.validator = validator;
    }

    @Operation(summary = "Retorna todas")
    @GET
    public Response getAll(){
        List<EntradaSaidaModel> lista = repository.listAll();
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lista).build();
    }

    @GET
    @Path("{id}")
    public Response getEntradaSaidaById(@PathParam("id") long id){
        EntradaSaidaModel model = repository.findById(id);
        if(model != null){
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Transactional
    public Response addEntradaSaida(EntradaSaidaDTO entradaSaidaDTO){
        Set<ConstraintViolation<EntradaSaidaDTO>> violations = validator.validate(entradaSaidaDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }
        if(entradaSaidaDTO.getDataHoraEntrada() == null && entradaSaidaDTO.getDataHoraSaida() == null){
            String message = Json.object().put("message","Data de entrada e data da saída não podem ser ambas nulas.").build();
            return Response.status( 422).entity(message).type(MediaType.APPLICATION_JSON).build();
        }

        EntradaSaidaModel model = new EntradaSaidaModel();
        model.setDataHoraEntrada(entradaSaidaDTO.getDataHoraEntrada());
        model.setDataHoraSaida(entradaSaidaDTO.getDataHoraSaida());
        model.setDescricao(entradaSaidaDTO.getDescricao());

        if(residenteRepository.findById(entradaSaidaDTO.getResidente()) != null){
            ResidenteModel residenteModel = residenteRepository.findById(entradaSaidaDTO.getResidente());
            model.setResidente(residenteModel);
        }else{
            return Response.status( Response.Status.NOT_FOUND.getStatusCode(),"Residente não encontrado.").build();
        }

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
    public Response updateEntradaSaida(@PathParam("id") long id, EntradaSaidaDTO entradaSaidaDTO){
        Set<ConstraintViolation<EntradaSaidaDTO>> violations = validator.validate(entradaSaidaDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }
        if(entradaSaidaDTO.getDataHoraEntrada() == null && entradaSaidaDTO.getDataHoraSaida() == null){
            String message = Json.object().put("message","Data de entrada e data da saída não podem ser ambas nulas.").build();
            return Response.status( 422).entity(message).type(MediaType.APPLICATION_JSON).build();
        }
        EntradaSaidaModel model = repository.findById(id);
        if(model != null){
            model.setDataHoraEntrada(entradaSaidaDTO.getDataHoraEntrada());
            model.setDataHoraSaida(entradaSaidaDTO.getDataHoraSaida());
            model.setDescricao(entradaSaidaDTO.getDescricao());

            if(residenteRepository.findById(entradaSaidaDTO.getResidente()) != null){
                ResidenteModel residenteModel = residenteRepository.findById(entradaSaidaDTO.getResidente());
                model.setResidente(residenteModel);
            }else{
                return Response.status( Response.Status.NOT_FOUND.getStatusCode(),"Residente não encontrado.").build();
            }

            return Response.status(201, model.toString()).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteEntradaSaida(@PathParam("id") long id){
        EntradaSaidaModel model = repository.findById(id);
        if(model != null){
            repository.delete(model);
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
