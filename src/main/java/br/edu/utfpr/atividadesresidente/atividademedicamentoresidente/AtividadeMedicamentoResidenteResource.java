package br.edu.utfpr.atividadesresidente.atividademedicamentoresidente;

import br.edu.utfpr.atividadesresidente.AtividadeResidenteDTO;
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

@Path("/atividademedicamento")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "AtividadeMedicamento")
public class AtividadeMedicamentoResidenteResource {

    private AtividadeMedicamentoResidenteService service;

    @Inject
    public AtividadeMedicamentoResidenteResource(AtividadeMedicamentoResidenteService service){
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
    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") long id, AtividadeResidenteDTO atividadeResidenteDTO){
        return service.update(id, atividadeResidenteDTO);
    }

    @RolesAllowed({"ADMIN","CUIDADOR","VOLUNTARIO"})
    @GET
    @Path("pagesort/{page}/{size}/{sort}/{asc}")
    public Response page(@PathParam("page") int page, @PathParam("size") int size, @PathParam("sort") String sort, @PathParam("asc") boolean asc){
        return service.pageSort(page,size,sort,asc);
    }

}
