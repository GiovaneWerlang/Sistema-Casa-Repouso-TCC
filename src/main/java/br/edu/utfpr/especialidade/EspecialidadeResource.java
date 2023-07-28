package br.edu.utfpr.especialidade;

import br.edu.utfpr.crud.CrudResource;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/especialidade")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Especialidade")
public class EspecialidadeResource implements CrudResource<EspecialidadeDTO> {

    private EspecialidadeService service;

    @Inject
    public EspecialidadeResource(EspecialidadeService service){
        this.service = service;
    }

    @Operation(summary = "Retorna todas")
    @GET
    @RolesAllowed("FUNCIONARIO")
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
    public Response add(EspecialidadeDTO especialidadeDTO){
       return service.add(especialidadeDTO);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") long id, EspecialidadeDTO especialidadeDTO){
       return service.update(id, especialidadeDTO);
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
