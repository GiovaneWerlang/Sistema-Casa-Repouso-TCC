package br.edu.utfpr.exame;

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

@Path("/exame")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Exame")
public class ExameResource implements CrudResource<ExameDTO> {

    private ExameService service;

    @Inject
    public ExameResource(ExameService service){
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
    public Response add(ExameDTO exameDTO){
       return service.add(exameDTO);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") long id, ExameDTO exameDTO){
        return service.update(id, exameDTO);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") long id){
        return service.delete(id);
    }
}
