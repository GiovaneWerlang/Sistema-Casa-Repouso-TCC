package br.edu.utfpr.atividadeludica;

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

@Path("/atividadeludica")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Atividade Lúdica")
public class AtividadeLudicaResource implements CrudResource<AtividadeLudicaDTO> {

    private AtividadeLudicaService service;

    @Inject
    public AtividadeLudicaResource(AtividadeLudicaService service){
        this.service = service;
    }

    @Operation(summary = "Retorna todas")
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
    public Response add(AtividadeLudicaDTO atividadeLudicaDTO){
        return service.add(atividadeLudicaDTO);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") long id, AtividadeLudicaDTO atividadeLudicaDTO){
        return service.update(id, atividadeLudicaDTO);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") long id){
        return service.delete(id);
    }

    @GET
    @Path("page/{page}/{size}")
    public Response page(@PathParam("page") int page, @PathParam("size") int size){
        return service.page(page,size);
    }

}
