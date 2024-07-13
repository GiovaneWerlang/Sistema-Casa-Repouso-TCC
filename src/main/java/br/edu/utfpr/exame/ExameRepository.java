package br.edu.utfpr.exame;

import br.edu.utfpr.crud.CrudRepository;
import br.edu.utfpr.dashboard.DadoDTO;
import io.quarkus.panache.common.Parameters;

import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class ExameRepository extends CrudRepository<ExameModel> {

    public List<DadoDTO> getDadosDash(LocalDateTime dataInicial, LocalDateTime dataFinal){
        return find("select 'Exames' as label, count(e.id) as data from ExameModel e where e.dataHora between :dataInicial and :dataFinal",
                Parameters.with("dataInicial", dataInicial).and("dataFinal", dataFinal)
        ).project(DadoDTO.class).list();
    }
}
