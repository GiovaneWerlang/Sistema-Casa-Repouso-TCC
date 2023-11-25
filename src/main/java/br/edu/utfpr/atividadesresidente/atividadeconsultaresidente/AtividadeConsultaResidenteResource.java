package br.edu.utfpr.atividadesresidente.atividadeconsultaresidente;

import br.edu.utfpr.atividadesresidente.AtividadeResidenteDTO;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

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
    @GET
    public Response getAll(@Context SecurityContext ctx){
        return service.getAll();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") long id){
        return service.findById(id);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") long id, AtividadeResidenteDTO atividadeResidenteDTO){
        return service.update(id, atividadeResidenteDTO);
    }

    @GET
    @Path("pagesort/{page}/{size}/{sort}/{asc}")
    public Response page(@PathParam("page") int page, @PathParam("size") int size, @PathParam("sort") String sort, @PathParam("asc") boolean asc){
        return service.pageSort(page,size,sort,asc);
    }

}
