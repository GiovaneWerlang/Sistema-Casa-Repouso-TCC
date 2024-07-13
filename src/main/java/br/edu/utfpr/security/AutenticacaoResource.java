package br.edu.utfpr.security;

import br.edu.utfpr.usuario.UsuarioDTO;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/autenticacao")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Autenticação")
public class AutenticacaoResource {

    private AutenticacaoService service;

    @Inject
    public AutenticacaoResource(AutenticacaoService service){
        this.service = service;
    }

    @POST
    @Path("/login")
    public Response login(UsuarioDTO usuarioDTO) {
        return service.getDadosUsuario(usuarioDTO.getLogin(), usuarioDTO.getSenha());
    }

}
