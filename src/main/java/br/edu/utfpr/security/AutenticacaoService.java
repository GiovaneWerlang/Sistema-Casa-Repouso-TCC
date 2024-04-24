package br.edu.utfpr.security;

import br.edu.utfpr.usuario.UsuarioDados;
import br.edu.utfpr.usuario.UsuarioModel;
import br.edu.utfpr.usuario.UsuarioRepository;
import br.edu.utfpr.utils.ResponseUtils;
import io.quarkus.elytron.security.common.BcryptUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.time.Instant;
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
                return ResponseUtils.notAuth();
            }
            Security security = new Security();
            Instant instant = Instant.now().plus(1, ChronoUnit.HOURS);
            UsuarioDados usuarioDados = new UsuarioDados(
                    security.token(
                            model.getProfissional().getFuncao(),
                            model.getProfissional().getNome(),
                            String.valueOf(model.getProfissional().getId()),
                            instant
                    )
            );
            return ResponseUtils.okModel(
                    usuarioDados
            );
        }

        return ResponseUtils.notFound();
    }

}
