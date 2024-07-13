package br.edu.utfpr.atividadesresidente.atividademedicamentoresidente;

import br.edu.utfpr.crud.CrudRepositoryAtividade;
import br.edu.utfpr.dashboard.AtividadeDashDTO;
import br.edu.utfpr.dashboard.DadoDTO;
import br.edu.utfpr.enums.SituacaoAtividade;
import io.quarkus.panache.common.Parameters;

import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.util.Arrays;
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

    public List<AtividadeDashDTO> findByTimeDTO(){
        return find("select a.id, a.descricao, a.dataHora, 'Medicamento' as tipo from AtividadeMedicamentoResidenteModel a where a.dataHora between :dataHoraInicial and :dataHoraFinal and a.situacao in (:situacoes)",
                Parameters.with("dataHoraInicial", LocalDateTime.now().withHour(5)).and("dataHoraFinal", LocalDateTime.now().plusDays(1).withHour(8)).and("situacoes", Arrays.asList(SituacaoAtividade.PENDENTE, SituacaoAtividade.ENVIADA))
        ).project(AtividadeDashDTO.class).list();
    }

}
