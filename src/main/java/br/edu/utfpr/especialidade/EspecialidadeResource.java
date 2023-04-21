package br.edu.utfpr.especialidade;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/especialidade")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Especialidade")
public class EspecialidadeResource {

    private EspecialidadeService service;

    @Inject
    public EspecialidadeResource(EspecialidadeService service){
        this.service = service;
    }

    @Operation(summary = "Retorna todas")
    @GET
    public Response getAll(){
        return service.getAll();
    }

    @GET
    @Path("{id}")
    public Response getEspecialidadeById(@PathParam("id") long id){
       return service.findById(id);
    }
    @POST
    @Transactional
    public Response addEspecialidade(EspecialidadeDTO especialidadeDTO){
       return service.add(especialidadeDTO);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateEspecialidade(@PathParam("id") long id, EspecialidadeDTO especialidadeDTO){
       return service.update(id, especialidadeDTO);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteEspecialidade(@PathParam("id") long id){
        return service.delete(id);
    }

}
