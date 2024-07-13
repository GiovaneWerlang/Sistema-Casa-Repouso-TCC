package br.edu.utfpr.residente;

import br.edu.utfpr.crud.CrudResource;
import jakarta.annotation.security.RolesAllowed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@Path("/residente")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Residente")
public class ResidenteResource implements CrudResource<ResidenteDTO> {

    private ResidenteService service;

    @Inject
    public ResidenteResource(ResidenteService service){
        this.service = service;
    }

    @Operation(summary = "Retorna todos")
    @RolesAllowed({"ADMIN","CUIDADOR","VOLUNTARIO"})
    @GET
    public Response getAll(@Context SecurityContext ctx){
        return service.getAll();
    }

    @RolesAllowed({"ADMIN","CUIDADOR","VOLUNTARIO"})
    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") long id){
        return service.findById(id);
    }

    @RolesAllowed({"ADMIN","CUIDADOR","VOLUNTARIO"})
    @POST
    @Transactional
    public Response add(ResidenteDTO residenteDTO){
        return service.add(residenteDTO);
    }

    @RolesAllowed({"ADMIN","CUIDADOR","VOLUNTARIO"})
    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") long id, ResidenteDTO residenteDTO){
        return service.update(id, residenteDTO);
    }

    @RolesAllowed({"ADMIN","CUIDADOR","VOLUNTARIO"})
    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") long id){
        return service.delete(id);
    }

    @RolesAllowed({"ADMIN","CUIDADOR","VOLUNTARIO"})
    @GET
    @Path("pagesort/{page}/{size}/{sort}/{asc}")
    public Response pageSort(@PathParam("page") int page, @PathParam("size") int size,@PathParam("sort") String sort,@PathParam("asc") boolean asc){
        return service.pageSort(page,size,sort,asc);
    }

}
