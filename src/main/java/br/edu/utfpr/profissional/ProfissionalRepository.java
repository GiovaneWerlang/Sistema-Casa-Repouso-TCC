package br.edu.utfpr.profissional;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProfissionalRepository implements PanacheRepository<ProfissionalModel> {
}
