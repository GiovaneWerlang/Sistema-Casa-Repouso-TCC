package br.edu.utfpr.medicamentoestoque;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MedicamentoEstoqueRepository implements PanacheRepository<MedicamentoEstoqueModel> {
}
