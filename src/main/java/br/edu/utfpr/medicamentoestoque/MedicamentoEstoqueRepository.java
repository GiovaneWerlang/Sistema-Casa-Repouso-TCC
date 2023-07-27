package br.edu.utfpr.medicamentoestoque;

import br.edu.utfpr.crud.CrudRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MedicamentoEstoqueRepository extends CrudRepository<MedicamentoEstoqueModel> {
}
