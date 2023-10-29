package br.edu.utfpr.security;

import br.edu.utfpr.usuario.UsuarioDados;
import br.edu.utfpr.usuario.UsuarioModel;
import br.edu.utfpr.usuario.UsuarioRepository;
import io.quarkus.elytron.security.common.BcryptUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

@ApplicationScoped
public class AutenticacaoService {

    private UsuarioRepository repository;

    @Inject
    public AutenticacaoService(UsuarioRepository repository){
        this.repository = repository;
    }

    public Response getDadosUsuario(String login, String senha) {
        UsuarioModel model = repository.findByLogin(login);

        if(model != null){
            if(!BcryptUtil.matches(senha, model.getSenha())){
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
            Security security = new Security();
            Instant instant = Instant.now().plus(7, ChronoUnit.DAYS);
            UsuarioDados usuarioDados = new UsuarioDados(
                    model.getProfissional().getId(),
                    model.getProfissional().getNome(),
                    security.token(
                            model.getProfissional().getFuncao(),
                            model.getProfissional().getNome(),
                            String.valueOf(model.getProfissional().getId()),
                            instant
                    ),
                    model.getProfissional().getFuncao(),
                    LocalDateTime.ofInstant(instant, ZoneId.of("America/Sao_Paulo"))
            );
            return Response.ok(
                    usuarioDados
            ).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
