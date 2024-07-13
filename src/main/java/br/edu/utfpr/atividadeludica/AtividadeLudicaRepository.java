package br.edu.utfpr.atividadeludica;

import br.edu.utfpr.crud.CrudRepository;
import br.edu.utfpr.dashboard.DadoDTO;
import io.quarkus.panache.common.Parameters;

import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class AtividadeLudicaRepository extends CrudRepository<AtividadeLudicaModel> {

    public List<DadoDTO> getDadosDash(LocalDateTime dataInicial, LocalDateTime dataFinal){
        return find("select 'Atv. Lúdica' as label, count(a.id) as data from AtividadeLudicaModel a where a.dataHora between :dataInicial and :dataFinal",
                Parameters.with("dataInicial", dataInicial).and("dataFinal", dataFinal)
        ).project(DadoDTO.class).list();
    }
}
