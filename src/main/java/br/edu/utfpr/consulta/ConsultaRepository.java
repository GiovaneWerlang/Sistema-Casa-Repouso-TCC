package br.edu.utfpr.consulta;

import br.edu.utfpr.crud.CrudRepository;
import br.edu.utfpr.dashboard.DadoDTO;
import io.quarkus.panache.common.Parameters;

import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class ConsultaRepository extends CrudRepository<ConsultaModel> {

    public List<DadoDTO> getDadosDash(LocalDateTime dataInicial, LocalDateTime dataFinal){
        return find("select 'Consultas' as label, count(c.id) as data from ConsultaModel c where c.dataHora between :dataInicial and :dataFinal",
                Parameters.with("dataInicial", dataInicial).and("dataFinal", dataFinal)
        ).project(DadoDTO.class).list();
    }
}
