package br.edu.utfpr.usuario;


import br.edu.utfpr.crud.CrudResource;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Usuário")
public class UsuarioResource implements CrudResource<UsuarioDTO> {

    private UsuarioService service;

    @Inject
    public UsuarioResource(UsuarioService service){
        this.service = service;

    }

    @Operation(summary = "Retorna todos")
    @GET
    public Response getAll(@Context SecurityContext ctx){
        return service.getAll();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") long id){
        return service.findById(id);
    }
    @POST
    @Transactional
    public Response add(UsuarioDTO usuarioDTO){
       return service.add(usuarioDTO);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") long id, UsuarioDTO usuarioDTO){
        return service.update(id, usuarioDTO);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") long id){
        return service.delete(id);
    }

    @POST
    @Path("/login")
    public Response login(UsuarioDTO usuarioDTO) {
        return service.getDadosUsuario(usuarioDTO.getLogin(), usuarioDTO.getSenha());
    }

    @GET
    @Path("page/{page}/{size}")
    public Response page(@PathParam("page") int page, @PathParam("size") int size){
        return service.page(page,size);
    }

}
