package br.edu.utfpr.atividadesresidente.atividademedicamentoresidente;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AtividadeMedicamentoResidenteRepository implements PanacheRepository<AtividadeMedicamentoResidenteModel> {

    public AtividadeMedicamentoResidenteModel findByMedicamentoId(Long id){
        return find("idmedicamento", id).firstResult();
    }
}
