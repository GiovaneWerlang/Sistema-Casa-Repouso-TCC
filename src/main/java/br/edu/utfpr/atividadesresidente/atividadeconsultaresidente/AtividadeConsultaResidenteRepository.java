package br.edu.utfpr.atividadesresidente.atividadeconsultaresidente;

import br.edu.utfpr.crud.CrudRepositoryAtividade;
import br.edu.utfpr.dashboard.DadoDTO;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
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

}
