package br.edu.utfpr.consulta;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

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
    @GET
    public Response getAll(@Context SecurityContext ctx){
        return service.getAll();
    }

    @GET
    @Path("{id}")
    public Response getConsultaById(@PathParam("id") long id){
        return service.findById(id);
    }

    @POST
    @Transactional
    public Response addConsulta(ConsultaDTO consultaDTO){
       return service.add(consultaDTO);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateConsulta(@PathParam("id") long id, ConsultaDTO consultaDTO){
        return service.update(id, consultaDTO);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteConsulta(@PathParam("id") long id){
        return service.delete(id);
    }

    @GET
    @Path("page/{page}/{size}")
    public Response page(@PathParam("page") int page, @PathParam("size") int size){
        return service.page(page,size);
    }

}
