package br.edu.utfpr.atividadesresidente.atividadeconsultaresidente;

import br.edu.utfpr.crud.CrudRepositoryAtividade;
import br.edu.utfpr.dashboard.AtividadeDashDTO;
import br.edu.utfpr.dashboard.DadoDTO;
import br.edu.utfpr.enums.SituacaoAtividade;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class AtividadeConsultaResidenteRepository extends CrudRepositoryAtividade<AtividadeConsultaResidenteModel> {
    public AtividadeConsultaResidenteModel findByConsultaId(Long id){
        return find("idconsulta", id).firstResult();
    }

    public List<DadoDTO> getDadosDash(LocalDateTime dataInicial, LocalDateTime dataFinal){
        return find("select " +
                        "ac.situacao as label, " +
                        "count(ac.id) as data " +
                        "from AtividadeConsultaResidenteModel ac " +
                        "where ac.dataHora between :dataInicial and :dataFinal " +
                        "group by ac.situacao",
                Parameters.with("dataInicial", dataInicial).and("dataFinal", dataFinal)
        ).project(DadoDTO.class).list();
    }

    public List<AtividadeDashDTO> findByTimeDTO(){
        return find("select a.id, a.descricao, a.dataHora, 'Consulta' as tipo from AtividadeConsultaResidenteModel a where a.dataHora between :dataHoraInicial and :dataHoraFinal and a.situacao in (:situacoes)",
                Parameters.with("dataHoraInicial", LocalDateTime.now().withHour(5)).and("dataHoraFinal", LocalDateTime.now().plusDays(1).withHour(8)).and("situacoes", Arrays.asList(SituacaoAtividade.PENDENTE, SituacaoAtividade.ENVIADA))
        ).project(AtividadeDashDTO.class).list();
    }

}
