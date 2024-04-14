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
    @RolesAllowed({"ADMIN","CUIDADOR","ENFERMEIRO","FUNCIONARIO","MEDICO","VOLUNTARIO"})
    public Response getAll(@Context SecurityContext ctx){
        return service.getAll();
    }

    @RolesAllowed({"ADMIN","CUIDADOR","ENFERMEIRO","FUNCIONARIO","MEDICO","VOLUNTARIO"})
    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") long id){
       return service.findById(id);
    }

    @RolesAllowed({"ADMIN","CUIDADOR","ENFERMEIRO","FUNCIONARIO","MEDICO","VOLUNTARIO"})
    @POST
    @Transactional
    public Response add(EspecialidadeDTO especialidadeDTO){
       return service.add(especialidadeDTO);
    }

    @RolesAllowed({"ADMIN","CUIDADOR","ENFERMEIRO","FUNCIONARIO","MEDICO","VOLUNTARIO"})
    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") long id, EspecialidadeDTO especialidadeDTO){
       return service.update(id, especialidadeDTO);
    }

    @RolesAllowed({"ADMIN","CUIDADOR","ENFERMEIRO","FUNCIONARIO","MEDICO","VOLUNTARIO"})
    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") long id){
        return service.delete(id);
    }

    @RolesAllowed({"ADMIN","CUIDADOR","ENFERMEIRO","FUNCIONARIO","MEDICO","VOLUNTARIO"})
    @GET
    @Path("pagesort/{page}/{size}/{sort}/{asc}")
    public Response pageSort(@PathParam("page") int page, @PathParam("size") int size,@PathParam("sort") String sort,@PathParam("asc") boolean asc){
        return service.pageSort(page,size,sort,asc);
    }

}
