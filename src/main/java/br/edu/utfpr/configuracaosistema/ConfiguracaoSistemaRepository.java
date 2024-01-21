package br.edu.utfpr.configuracaosistema;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConfiguracaoSistemaRepository implements PanacheRepository<ConfiguracaoSistemaModel> {
}
