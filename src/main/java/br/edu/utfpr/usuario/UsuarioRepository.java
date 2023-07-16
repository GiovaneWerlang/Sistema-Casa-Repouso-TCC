package br.edu.utfpr.usuario;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<UsuarioModel> {

    public UsuarioModel findByLoginAndPasswordId(String login, String senha){
        return find("login = ?1 and senha = ?2", login, senha).firstResult();
    }

}
