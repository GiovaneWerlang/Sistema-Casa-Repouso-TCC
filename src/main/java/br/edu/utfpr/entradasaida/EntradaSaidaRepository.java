package br.edu.utfpr.entradasaida;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EntradaSaidaRepository implements PanacheRepository<EntradaSaidaModel> {
}
