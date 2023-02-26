package br.edu.utfpr.endereco;

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

@Path("/endereco")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Endereço")
public class EnderecoResource {

    private EnderecoRepository repository;
    private Validator validator;

    @Inject
    public EnderecoResource(EnderecoRepository repository, Validator validator){
        this.repository = repository;
        this.validator = validator;
    }

    @Operation(summary = "Retorna todos")
    @GET
    public Response getAll(){
        List<EnderecoModel> lista = repository.listAll();
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lista).build();
    }

    @GET
    @Path("{id}")
    public Response getEnderecoById(@PathParam("id") long id){
        EnderecoModel model = repository.findById(id);
        if(model != null){
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Transactional
    public Response addEndereco(EnderecoDTO enderecoDTO){
        Set<ConstraintViolation<EnderecoDTO>> violations = validator.validate(enderecoDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        EnderecoModel model = new EnderecoModel();
        model.setLogradouro(enderecoDTO.getLogradouro());
        model.setNumero(enderecoDTO.getNumero());
        model.setBairro(enderecoDTO.getBairro());
        model.setMunicipio(enderecoDTO.getMunicipio());
        model.setCep(enderecoDTO.getCep());
        model.setEstado(enderecoDTO.getEstado());
        model.setPais(enderecoDTO.getPais());

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
    public Response updateEndereco(@PathParam("id") long id, EnderecoDTO enderecoDTO){
        Set<ConstraintViolation<EnderecoDTO>> violations = validator.validate(enderecoDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        EnderecoModel model = repository.findById(id);
        if(model != null){
            model.setLogradouro(enderecoDTO.getLogradouro());
            model.setNumero(enderecoDTO.getNumero());
            model.setBairro(enderecoDTO.getBairro());
            model.setMunicipio(enderecoDTO.getMunicipio());
            model.setCep(enderecoDTO.getCep());
            model.setEstado(enderecoDTO.getEstado());
            model.setPais(enderecoDTO.getPais());

            return Response.status(201, model.toString()).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteEndereco(@PathParam("id") long id){
        EnderecoModel model = repository.findById(id);
        if(model != null){
            repository.delete(model);
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
