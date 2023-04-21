package br.edu.utfpr.residente;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/residente")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Residente")
public class ResidenteResource {

    private ResidenteService service;


    @Inject
    public ResidenteResource(ResidenteService service){
        this.service = service;
    }

    @Operation(summary = "Retorna todos")
    @GET
    public Response getAll(){
        return service.getAll();
    }

    @GET
    @Path("{id}")
    public Response getResidenteById(@PathParam("id") long id){
        return service.findById(id);
    }

    @POST
    @Transactional
    public Response addResidente(ResidenteDTO residenteDTO){
        return service.add(residenteDTO);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateResidente(@PathParam("id") long id, ResidenteDTO residenteDTO){
        return service.update(id, residenteDTO);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteResidente(@PathParam("id") long id){
        return service.delete(id);
    }
}
