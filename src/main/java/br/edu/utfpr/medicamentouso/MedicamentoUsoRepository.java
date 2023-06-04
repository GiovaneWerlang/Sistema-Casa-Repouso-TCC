package br.edu.utfpr.medicamentouso;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MedicamentoUsoRepository implements PanacheRepository<MedicamentoUsoModel> {
}
