package br.edu.utfpr.movimentacaoestoque;

import br.edu.utfpr.crud.CrudResource;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/movimentacaoestoque")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Movimentacao Estoque")
public class MovimentacaoEstoqueResource implements CrudResource<MovimentacaoEstoqueDTO> {

    private MovimentacaoEstoqueService service;

    @Inject
    public MovimentacaoEstoqueResource(MovimentacaoEstoqueService service) {
        this.service = service;
    }

    @Operation(summary = "Retorna todos")
    @GET
    public Response getAll(){
        return service.getAll();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") long id){
        return service.findById(id);
    }
    @POST
    @Transactional
    public Response add(MovimentacaoEstoqueDTO movimentacaoEstoqueDTO){
        return service.add(movimentacaoEstoqueDTO);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") long id, MovimentacaoEstoqueDTO movimentacaoEstoqueDTO){
        return service.update(id, movimentacaoEstoqueDTO);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") long id){
        return service.delete(id);
    }

}
