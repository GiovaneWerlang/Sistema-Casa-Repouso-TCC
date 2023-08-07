package br.edu.utfpr.atividadesresidente.atividadeexameresidente;

import br.edu.utfpr.crud.CrudRepository;

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

}
