package br.edu.utfpr.atividadesresidente.atividademedicamentoresidente;

import br.edu.utfpr.crud.CrudRepository;
import br.edu.utfpr.dashboard.DadoDTO;
import io.quarkus.panache.common.Parameters;

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

    public List<DadoDTO> getDadosDash(LocalDateTime dataInicial, LocalDateTime dataFinal){
        return find("select " +
                        "case when  " +
                        "am.situacao is null then 'Pendente' else am.situacao end as label, " +
                        "count(am.id) as data " +
                        "from AtividadeMedicamentoResidenteModel am " +
                        "where am.dataHora between :dataInicial and :dataFinal " +
                        "group by am.situacao",
                Parameters.with("dataInicial", dataInicial).and("dataFinal", dataFinal)
        ).project(DadoDTO.class).list();
    }

}
