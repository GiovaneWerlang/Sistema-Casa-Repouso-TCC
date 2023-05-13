package br.edu.utfpr.atividadesresidente.atividadeexameresidente;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AtividadeExameResidenteRepository  implements PanacheRepository<AtividadeExameResidenteModel> {

    public AtividadeExameResidenteModel findByExameId(Long id){
        return find("idexame", id).firstResult();
    }

}
