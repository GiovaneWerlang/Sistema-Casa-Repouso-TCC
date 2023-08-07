package br.edu.utfpr.atividadesresidente.atividademedicamentoresidente;

import br.edu.utfpr.crud.CrudRepository;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class AtividadeMedicamentoResidenteRepository extends CrudRepository<AtividadeMedicamentoResidenteModel> {

    public AtividadeMedicamentoResidenteModel findByMedicamentoId(Long id){
        return find("idmedicamento", id).firstResult();
    }

    public List<AtividadeMedicamentoResidenteModel> findByTime(){
        return find("datahora between ?1 and ?2", LocalDateTime.now().withHour(5), LocalDateTime.now().plusDays(1).withHour(8)).list();
    }

}
