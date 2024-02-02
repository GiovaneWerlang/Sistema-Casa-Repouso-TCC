package br.edu.utfpr.atividadeludica;

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
    @RolesAllowed({"CUIDADOR","FUNCIONARIO","VOLUNTARIO"})
    @GET
    public Response getAll(@Context SecurityContext ctx){
        return service.getAll();
    }

    @RolesAllowed({"CUIDADOR","FUNCIONARIO","VOLUNTARIO"})
    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") long id){
       return service.findById(id);
    }

    @RolesAllowed({"CUIDADOR","FUNCIONARIO","VOLUNTARIO"})
    @POST
    @Transactional
    public Response add(AtividadeLudicaDTO atividadeLudicaDTO){
        return service.add(atividadeLudicaDTO);
    }

    @RolesAllowed({"CUIDADOR","FUNCIONARIO","VOLUNTARIO"})
    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") long id, AtividadeLudicaDTO atividadeLudicaDTO){
        return service.update(id, atividadeLudicaDTO);
    }

    @RolesAllowed({"CUIDADOR","FUNCIONARIO","VOLUNTARIO"})
    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") long id){
        return service.delete(id);
    }

    @RolesAllowed({"CUIDADOR","FUNCIONARIO","VOLUNTARIO"})
    @GET
    @Path("page/{page}/{size}")
    public Response page(@PathParam("page") int page, @PathParam("size") int size){
        return service.page(page,size);
    }

    @RolesAllowed({"CUIDADOR","FUNCIONARIO","VOLUNTARIO"})
    @GET
    @Path("pagesort/{page}/{size}/{sort}/{asc}")
    public Response page(@PathParam("page") int page, @PathParam("size") int size,@PathParam("sort") String sort,@PathParam("asc") boolean asc){
        return service.pageSort(page,size,sort,asc);
    }

}
