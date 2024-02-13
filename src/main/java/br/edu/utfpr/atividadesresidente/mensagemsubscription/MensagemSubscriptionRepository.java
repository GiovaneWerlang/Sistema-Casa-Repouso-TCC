package br.edu.utfpr.atividadesresidente.mensagemsubscription;

import br.edu.utfpr.crud.CrudRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MensagemSubscriptionRepository extends CrudRepository<MensagemSubscription> {

    public MensagemSubscription findByAtt(MensagemSubscription mensagemSubscription){
        return find("endpoint = ?1 and p256dh = ?2 and auth = ?3", mensagemSubscription.getEndpoint(), mensagemSubscription.getP256dh(), mensagemSubscription.getAuth()).firstResult();
    }

}
