package br.edu.utfpr.atividadesresidente.mensagemsubscription;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class MensagemSubscriptionService {

    private MensagemSubscriptionRepository repository;

    @Inject
    public MensagemSubscriptionService(MensagemSubscriptionRepository repository) {
        this.repository = repository;
    }

    public List<MensagemSubscription> buscarTodos(){
        List<MensagemSubscription> lista = repository.listAll();

        return lista;
    }

    public Response add(MensagemSubscription mensagemSubscription){

        if( mensagemSubscription == null ||
            mensagemSubscription.getEndpoint() == null||
            mensagemSubscription.getP256dh() == null ||
            mensagemSubscription.getAuth() == null){
            return Response.status(422).build();
        }

        try{
            MensagemSubscription model = repository.findByAtt(mensagemSubscription);
            if(model != null) {
                mensagemSubscription.setId(model.getId());
            }
            repository.persist(mensagemSubscription);
        }catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.CREATED.getStatusCode()).entity(mensagemSubscription.getId()).build();
    }

    public Response delete(MensagemSubscription mensagemSubscription){
        MensagemSubscription model = repository.findByAtt(mensagemSubscription);
        if(model != null){
            repository.delete(model);
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
