package br.edu.utfpr.movimentacaoestoque;

import br.edu.utfpr.crud.CrudRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovimentacaoEstoqueRepository extends CrudRepository<MovimentacaoEstoqueModel> {
}
