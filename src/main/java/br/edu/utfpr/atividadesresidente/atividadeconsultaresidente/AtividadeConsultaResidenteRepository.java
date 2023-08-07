package br.edu.utfpr.atividadesresidente.atividadeconsultaresidente;

import br.edu.utfpr.crud.CrudRepository;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class AtividadeConsultaResidenteRepository extends CrudRepository<AtividadeConsultaResidenteModel> {
    public AtividadeConsultaResidenteModel findByConsultaId(Long id){
        return find("idconsulta", id).firstResult();
    }

    public List<AtividadeConsultaResidenteModel> findByTime(){
        return find("datahora between ?1 and ?2", LocalDateTime.now().withHour(5), LocalDateTime.now().plusDays(1).withHour(8)).list();
    }

}
