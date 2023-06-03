package br.edu.utfpr.atividadeludica;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/atividadeludica")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Atividade Lúdica")
public class AtividadeLudicaResource {

    private AtividadeLudicaService atividadeLudicaService;

    @Inject
    public AtividadeLudicaResource(AtividadeLudicaService atividadeLudicaService){
        this.atividadeLudicaService = atividadeLudicaService;
    }

    @Operation(summary = "Retorna todas")
    @GET
    public Response getAll(){
        return atividadeLudicaService.getAll();
    }

    @GET
    @Path("{id}")
    public Response getAtividadeLudicaById(@PathParam("id") long id){
       return atividadeLudicaService.findById(id);
    }

    @POST
    @Transactional
    public Response addAtividadeLudica(AtividadeLudicaDTO atividadeLudicaDTO){
        return atividadeLudicaService.add(atividadeLudicaDTO);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateAtividadeLudica(@PathParam("id") long id, AtividadeLudicaDTO atividadeLudicaDTO){
        return atividadeLudicaService.update(id, atividadeLudicaDTO);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteAtividadeLudica(@PathParam("id") long id){
        return atividadeLudicaService.delete(id);
    }

}
