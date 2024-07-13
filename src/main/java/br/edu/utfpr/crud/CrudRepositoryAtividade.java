package br.edu.utfpr.crud;

import br.edu.utfpr.enums.SituacaoAtividade;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public abstract class CrudRepositoryAtividade<T> extends CrudRepository<T> implements PanacheRepository<T> {

    public List<T> findByTime(){
        return find("dataHora between ?1 and ?2 and situacao in ?3", LocalDateTime.now().withHour(5), LocalDateTime.now().plusDays(1).withHour(8), Arrays.asList(SituacaoAtividade.PENDENTE, SituacaoAtividade.ENVIADA)).list();
    }

    public List<T> findToSendByDatahoraSituacao(){
        return find("dataHora between ?1 and ?2 and situacao = ?3", LocalDateTime.now(), LocalDateTime.now().plusHours(2), SituacaoAtividade.PENDENTE).list();
    }

    public int atualizarSituacaoEnviada(Long id){
        return update("situacaoatividade = 'ENVIADA', idprofissional = 1 where id = ?1", id);
    }
}
