package br.edu.utfpr.profissional;

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

@Path("/profissional")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Profissional")
public class ProfissionalResource implements CrudResource<ProfissionalDTO> {

    private ProfissionalService service;

    @Inject
    public ProfissionalResource(ProfissionalService service){
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
    public Response add(ProfissionalDTO profissionalDTO){
        return service.add(profissionalDTO);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") long id, ProfissionalDTO profissionalDTO){
        return service.update(id, profissionalDTO);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") long id){
        return service.delete(id);
    }
}
