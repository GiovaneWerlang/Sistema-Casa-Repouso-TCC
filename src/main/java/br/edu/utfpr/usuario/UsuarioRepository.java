package br.edu.utfpr.usuario;

import br.edu.utfpr.crud.CrudRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository extends CrudRepository<UsuarioModel> {

    public UsuarioModel findByLoginAndPasswordId(String login, String senha){
        return find("login = ?1 and senha = ?2", login, senha).firstResult();
    }

}
