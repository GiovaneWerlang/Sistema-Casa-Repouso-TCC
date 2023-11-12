package br.edu.utfpr.atividadesresidente.atividadeexameresidente;

import br.edu.utfpr.crud.CrudRepository;
import br.edu.utfpr.dashboard.DadoDTO;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class AtividadeExameResidenteRepository extends CrudRepository<AtividadeExameResidenteModel> {

    public AtividadeExameResidenteModel findByExameId(Long id){
        return find("idexame", id).firstResult();
    }

    public List<AtividadeExameResidenteModel> findByTime(){
        return find("datahora between ?1 and ?2", LocalDateTime.now().withHour(5), LocalDateTime.now().plusDays(1).withHour(8)).list();
    }

    public List<DadoDTO> getDadosDash(LocalDateTime dataInicial, LocalDateTime dataFinal){
        return find("select " +
                        "case when  " +
                        "ae.situacao is null then 'Pendente' else ae.situacao end as label, " +
                        "count(ae.id) as data " +
                        "from AtividadeExameResidenteModel ae " +
                        "where ae.dataHora between :dataInicial and :dataFinal " +
                        "group by ae.situacao",
                Parameters.with("dataInicial", dataInicial).and("dataFinal", dataFinal)
        ).project(DadoDTO.class).list();
    }

}
