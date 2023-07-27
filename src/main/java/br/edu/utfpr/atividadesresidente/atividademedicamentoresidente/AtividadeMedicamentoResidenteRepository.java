package br.edu.utfpr.atividadesresidente.atividademedicamentoresidente;

import br.edu.utfpr.crud.CrudRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AtividadeMedicamentoResidenteRepository extends CrudRepository<AtividadeMedicamentoResidenteModel> {

    public AtividadeMedicamentoResidenteModel findByMedicamentoId(Long id){
        return find("idmedicamento", id).firstResult();
    }
}
