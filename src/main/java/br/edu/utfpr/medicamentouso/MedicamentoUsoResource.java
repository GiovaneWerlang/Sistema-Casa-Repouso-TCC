package br.edu.utfpr.medicamentouso;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/medicamentouso")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Medicamento Uso")
public class MedicamentoUsoResource {

    private MedicamentoUsoService service;

    @Inject
    public MedicamentoUsoResource(MedicamentoUsoService service) {
        this.service = service;
    }

    @Operation(summary = "Retorna todos")
    @GET
    public Response getAll(){
        return service.getAll();
    }

    @GET
    @Path("{id}")
    public Response getExameById(@PathParam("id") long id){
        return service.findById(id);
    }

    @POST
    @Transactional
    public Response addExame(MedicamentoUsoDTO medicamentoUsoDTO){
        return service.add(medicamentoUsoDTO);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateExame(@PathParam("id") long id, MedicamentoUsoDTO medicamentoUsoDTO){
        return service.update(id, medicamentoUsoDTO);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteExame(@PathParam("id") long id){
        return service.delete(id);
    }

}
