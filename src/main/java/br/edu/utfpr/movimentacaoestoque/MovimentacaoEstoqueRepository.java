package br.edu.utfpr.movimentacaoestoque;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovimentacaoEstoqueRepository implements PanacheRepository<MovimentacaoEstoqueModel> {
}
