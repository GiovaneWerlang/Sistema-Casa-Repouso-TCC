package br.edu.utfpr.medicamentouso;

import br.edu.utfpr.crud.CrudRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MedicamentoUsoRepository extends CrudRepository<MedicamentoUsoModel> {
}
