package br.edu.utfpr.entradasaida;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/entradasaida")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Entrada Saída")
public class EntradaSaidaResource {

    private EntradaSaidaService service;

    @Inject
    public EntradaSaidaResource(EntradaSaidaService service){
        this.service = service;
    }

    @Operation(summary = "Retorna todas")
    @GET
    public Response getAll(){
        return service.getAll();
    }

    @GET
    @Path("{id}")
    public Response getEntradaSaidaById(@PathParam("id") long id){
        return service.findById(id);
    }

    @POST
    @Transactional
    public Response addEntradaSaida(EntradaSaidaDTO entradaSaidaDTO){
       return service.add(entradaSaidaDTO);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateEntradaSaida(@PathParam("id") long id, EntradaSaidaDTO entradaSaidaDTO){
        return service.update(id, entradaSaidaDTO);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteEntradaSaida(@PathParam("id") long id){
        return service.delete(id);
    }
}
