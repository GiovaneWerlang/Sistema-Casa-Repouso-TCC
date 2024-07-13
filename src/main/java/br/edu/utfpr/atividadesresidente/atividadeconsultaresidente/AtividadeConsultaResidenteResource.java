package br.edu.utfpr.atividadesresidente.atividadeconsultaresidente;

import br.edu.utfpr.atividadesresidente.AtividadeResidenteDTO;
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

@Path("/atividadeconsulta")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "AtividadeConsulta")
public class AtividadeConsultaResidenteResource {

    private AtividadeConsultaResidenteService service;

    @Inject
    public AtividadeConsultaResidenteResource(AtividadeConsultaResidenteService service){
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
    public Response pageSort(@PathParam("page") int page, @PathParam("size") int size, @PathParam("sort") String sort, @PathParam("asc") boolean asc){
        return service.pageSort(page,size,sort,asc);
    }

}
