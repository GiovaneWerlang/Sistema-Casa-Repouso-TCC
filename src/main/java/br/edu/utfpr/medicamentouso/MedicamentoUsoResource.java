package br.edu.utfpr.medicamentouso;

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

@Path("/medicamentouso")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Medicamento Uso")
public class MedicamentoUsoResource implements CrudResource<MedicamentoUsoDTO> {

    private MedicamentoUsoService service;

    @Inject
    public MedicamentoUsoResource(MedicamentoUsoService service) {
        this.service = service;
    }

    @Operation(summary = "Retorna todos")
    @RolesAllowed({"CUIDADOR","ENFERMEIRO","MEDICO","VOLUNTARIO"})
    @GET
    public Response getAll(@Context SecurityContext ctx){
        return service.getAll();
    }

    @RolesAllowed({"CUIDADOR","ENFERMEIRO","MEDICO","VOLUNTARIO"})
    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") long id){
        return service.findById(id);
    }

    @RolesAllowed({"CUIDADOR","ENFERMEIRO","MEDICO","VOLUNTARIO"})
    @POST
    @Transactional
    public Response add(MedicamentoUsoDTO medicamentoUsoDTO){
        return service.add(medicamentoUsoDTO);
    }

    @RolesAllowed({"CUIDADOR","ENFERMEIRO","MEDICO","VOLUNTARIO"})
    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") long id, MedicamentoUsoDTO medicamentoUsoDTO){
        return service.update(id, medicamentoUsoDTO);
    }

    @RolesAllowed({"CUIDADOR","ENFERMEIRO","MEDICO","VOLUNTARIO"})
    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") long id){
        return service.delete(id);
    }

    @RolesAllowed({"CUIDADOR","ENFERMEIRO","MEDICO","VOLUNTARIO"})
    @GET
    @Path("page/{page}/{size}")
    public Response page(@PathParam("page") int page, @PathParam("size") int size){
        return service.page(page,size);
    }

    @RolesAllowed({"CUIDADOR","ENFERMEIRO","MEDICO","VOLUNTARIO"})
    @GET
    @Path("pagesort/{page}/{size}/{sort}/{asc}")
    public Response page(@PathParam("page") int page, @PathParam("size") int size,@PathParam("sort") String sort,@PathParam("asc") boolean asc){
        return service.pageSort(page,size,sort,asc);
    }

}
