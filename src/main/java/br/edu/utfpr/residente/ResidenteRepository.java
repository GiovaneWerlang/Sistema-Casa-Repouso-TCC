package br.edu.utfpr.residente;

import br.edu.utfpr.crud.CrudRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ResidenteRepository extends CrudRepository<ResidenteModel> {
}
