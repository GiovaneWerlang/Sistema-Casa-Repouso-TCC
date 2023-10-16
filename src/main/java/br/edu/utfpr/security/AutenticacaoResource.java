package br.edu.utfpr.security;

import br.edu.utfpr.usuario.UsuarioDTO;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
