package br.edu.utfpr.usuario;


import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Usuário")
public class UsuarioResource {

    private UsuarioService service;

    @Inject
    public UsuarioResource(UsuarioService service){
        this.service = service;

    }

    @Operation(summary = "Retorna todos")
    @GET
    public Response getAll(){
        return service.getAll();
    }

    @GET
    @Path("{id}")
    public Response getUsuarioById(@PathParam("id") long id){
        return service.findById(id);
    }
    @POST
    @Transactional
    public Response addUsuario(UsuarioDTO usuarioDTO){
       return service.add(usuarioDTO);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateUsuario(@PathParam("id") long id, UsuarioDTO usuarioDTO){
        return service.update(id, usuarioDTO);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteUsuario(@PathParam("id") long id){
        return service.delete(id);
    }
}
