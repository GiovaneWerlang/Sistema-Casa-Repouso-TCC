package br.edu.utfpr.atividadeludica;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AtividadeLudicaRepository implements PanacheRepository<AtividadeLudicaModel> {
}
