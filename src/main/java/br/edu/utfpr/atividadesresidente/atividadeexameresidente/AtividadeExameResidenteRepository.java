package br.edu.utfpr.atividadesresidente.atividadeexameresidente;

import br.edu.utfpr.crud.CrudRepositoryAtividade;
import br.edu.utfpr.dashboard.DadoDTO;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class AtividadeExameResidenteRepository extends CrudRepositoryAtividade<AtividadeExameResidenteModel> {

    public AtividadeExameResidenteModel findByExameId(Long id){
        return find("idexame", id).firstResult();
    }

    public List<DadoDTO> getDadosDash(LocalDateTime dataInicial, LocalDateTime dataFinal){
        return find("select " +
                        "ae.situacao as label, " +
                        "count(ae.id) as data " +
                        "from AtividadeExameResidenteModel ae " +
                        "where ae.dataHora between :dataInicial and :dataFinal " +
                        "group by ae.situacao",
                Parameters.with("dataInicial", dataInicial).and("dataFinal", dataFinal)
        ).project(DadoDTO.class).list();
    }

}
