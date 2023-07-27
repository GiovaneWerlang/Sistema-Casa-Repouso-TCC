package br.edu.utfpr.atividadesresidente.atividadeexameresidente;

import br.edu.utfpr.crud.CrudRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AtividadeExameResidenteRepository extends CrudRepository<AtividadeExameResidenteModel> {

    public AtividadeExameResidenteModel findByExameId(Long id){
        return find("idexame", id).firstResult();
    }

}
