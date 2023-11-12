package br.edu.utfpr.residente;

import br.edu.utfpr.crud.CrudRepository;
import br.edu.utfpr.dashboard.DadoDTO;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class ResidenteRepository extends CrudRepository<ResidenteModel> {
    public List<DadoDTO> getDadosEstadiaDash(){
        return find("select r.tipoEstadia as label, count(r.tipoEstadia) as data from ResidenteModel r group by r.tipoEstadia").project(DadoDTO.class).list();
    }
    public List<DadoDTO> getDadosIngresso30diasDash(LocalDateTime dataInicial, LocalDateTime dataFinal){
        return find("select 'Residentes' as label, count(r.id) as data from ResidenteModel r where r.dataHoraIngresso between :dataInicial and :dataFinal",
                Parameters.with("dataInicial", dataInicial).and("dataFinal", dataFinal)
        ).project(DadoDTO.class).list();
    }
}
