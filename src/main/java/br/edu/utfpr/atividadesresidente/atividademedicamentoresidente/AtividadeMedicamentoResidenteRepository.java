package br.edu.utfpr.atividadesresidente.atividademedicamentoresidente;

import br.edu.utfpr.crud.CrudRepositoryAtividade;
import br.edu.utfpr.dashboard.DadoDTO;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class AtividadeMedicamentoResidenteRepository extends CrudRepositoryAtividade<AtividadeMedicamentoResidenteModel> {

    public AtividadeMedicamentoResidenteModel findByMedicamentoId(Long id){
        return find("idmedicamento", id).firstResult();
    }

    public List<DadoDTO> getDadosDash(LocalDateTime dataInicial, LocalDateTime dataFinal){
        return find("select " +
                        "am.situacao as label, " +
                        "count(am.id) as data " +
                        "from AtividadeMedicamentoResidenteModel am " +
                        "where am.dataHora between :dataInicial and :dataFinal " +
                        "group by am.situacao",
                Parameters.with("dataInicial", dataInicial).and("dataFinal", dataFinal)
        ).project(DadoDTO.class).list();
    }

}
