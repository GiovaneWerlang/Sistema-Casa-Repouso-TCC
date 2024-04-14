package br.edu.utfpr.consulta;

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

@Path("/consulta")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Consulta")
public class ConsultaResource {

    private ConsultaService service;

    @Inject
    public ConsultaResource(ConsultaService service){
        this.service = service;
    }

    @Operation(summary = "Retorna todos")
    @RolesAllowed({"ADMIN","CUIDADOR","ENFERMEIRO","MEDICO"})
    @GET
    public Response getAll(@Context SecurityContext ctx){
        return service.getAll();
    }

    @RolesAllowed({"ADMIN","CUIDADOR","ENFERMEIRO","MEDICO"})
    @GET
    @Path("{id}")
    public Response getConsultaById(@PathParam("id") long id){
        return service.findById(id);
    }

    @RolesAllowed({"ADMIN","CUIDADOR","ENFERMEIRO","MEDICO"})
    @POST
    @Transactional
    public Response addConsulta(ConsultaDTO consultaDTO){
       return service.add(consultaDTO);
    }

    @RolesAllowed({"ADMIN","CUIDADOR","ENFERMEIRO","MEDICO"})
    @PUT
    @Path("{id}")
    @Transactional
    public Response updateConsulta(@PathParam("id") long id, ConsultaDTO consultaDTO){
        return service.update(id, consultaDTO);
    }

    @RolesAllowed({"ADMIN","CUIDADOR","ENFERMEIRO","MEDICO"})
    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteConsulta(@PathParam("id") long id){
        return service.delete(id);
    }

    @RolesAllowed({"ADMIN","CUIDADOR","ENFERMEIRO","MEDICO"})
    @GET
    @Path("pagesort/{page}/{size}/{sort}/{asc}")
    public Response pageSort(@PathParam("page") int page, @PathParam("size") int size,@PathParam("sort") String sort,@PathParam("asc") boolean asc){
        return service.pageSort(page,size,sort,asc);
    }

}
