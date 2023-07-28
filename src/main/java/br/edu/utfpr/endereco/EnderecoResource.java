package br.edu.utfpr.endereco;

import br.edu.utfpr.crud.CrudResource;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/endereco")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Endereço")
public class EnderecoResource implements CrudResource<EnderecoDTO> {

    private EnderecoService service;

    @Inject
    public EnderecoResource(EnderecoService service){
        this.service = service;
    }

    @Operation(summary = "Retorna todos")
    @GET
    public Response getAll(@Context SecurityContext ctx){
        return service.getAll();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") long id){
        return service.findById(id);
    }

    @POST
    @Transactional
    public Response add(EnderecoDTO enderecoDTO){
        return service.add(enderecoDTO);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") long id, EnderecoDTO enderecoDTO){
        return service.update(id, enderecoDTO);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") long id){
        return service.delete(id);
    }
}
