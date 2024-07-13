package br.edu.utfpr.movimentacaoestoque;

import br.edu.utfpr.crud.CrudResource;
import jakarta.annotation.security.RolesAllowed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

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
    @RolesAllowed({"ADMIN","CUIDADOR","FUNCIONARIO"})
    @GET
    public Response getAll(@Context SecurityContext ctx){
        return service.getAll();
    }

    @RolesAllowed({"ADMIN","CUIDADOR","FUNCIONARIO"})
    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") long id){
        return service.findById(id);
    }

    @RolesAllowed({"ADMIN","CUIDADOR","FUNCIONARIO"})
    @POST
    @Transactional
    public Response add(MovimentacaoEstoqueDTO movimentacaoEstoqueDTO){
        return service.add(movimentacaoEstoqueDTO);
    }

    @RolesAllowed({"ADMIN","CUIDADOR","FUNCIONARIO"})
    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") long id, MovimentacaoEstoqueDTO movimentacaoEstoqueDTO){
        return service.update(id, movimentacaoEstoqueDTO);
    }

    @RolesAllowed({"ADMIN","CUIDADOR","FUNCIONARIO"})
    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") long id){
        return service.delete(id);
    }

    @RolesAllowed({"ADMIN","CUIDADOR","FUNCIONARIO"})
    @GET
    @Path("pagesort/{page}/{size}/{sort}/{asc}")
    public Response pageSort(@PathParam("page") int page, @PathParam("size") int size,@PathParam("sort") String sort,@PathParam("asc") boolean asc){
        return service.pageSort(page,size,sort,asc);
    }

}
