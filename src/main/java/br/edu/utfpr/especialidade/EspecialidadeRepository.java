package br.edu.utfpr.especialidade;

import br.edu.utfpr.crud.CrudRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EspecialidadeRepository extends CrudRepository<EspecialidadeModel> {
}
