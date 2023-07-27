package br.edu.utfpr.movimentacaoestoque;

import br.edu.utfpr.crud.CrudRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovimentacaoEstoqueRepository extends CrudRepository<MovimentacaoEstoqueModel> {
}
