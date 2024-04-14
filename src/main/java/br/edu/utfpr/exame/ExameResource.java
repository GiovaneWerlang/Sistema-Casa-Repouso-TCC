package br.edu.utfpr.exame;

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

    @RolesAllowed({"ADMIN","CUIDADOR","ENFERMEIRO","MEDICO"})
    @Operation(summary = "Retorna todos")
    @GET
    public Response getAll(@Context SecurityContext ctx){
        return service.getAll();
    }

    @RolesAllowed({"ADMIN","CUIDADOR","ENFERMEIRO","MEDICO"})
    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") long id){
        return service.findById(id);
    }

    @RolesAllowed({"ADMIN","CUIDADOR","ENFERMEIRO","MEDICO"})
    @POST
    @Transactional
    public Response add(ExameDTO exameDTO){
       return service.add(exameDTO);
    }

    @RolesAllowed({"ADMIN","CUIDADOR","ENFERMEIRO","MEDICO"})
    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") long id, ExameDTO exameDTO){
        return service.update(id, exameDTO);
    }

    @RolesAllowed({"ADMIN","CUIDADOR","ENFERMEIRO","MEDICO"})
    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") long id){
        return service.delete(id);
    }

    @RolesAllowed({"ADMIN","CUIDADOR","ENFERMEIRO","MEDICO"})
    @GET
    @Path("pagesort/{page}/{size}/{sort}/{asc}")
    public Response pageSort(@PathParam("page") int page, @PathParam("size") int size,@PathParam("sort") String sort,@PathParam("asc") boolean asc){
        return service.pageSort(page,size,sort,asc);
    }

}
