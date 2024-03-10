package br.edu.utfpr.atividadesresidente.mensagemsubscription;

import br.edu.utfpr.utils.ResponseUtils;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/mensagemsubscription")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Subscrições")
public class MensagemSubscriptionResource {

    private MensagemSubscriptionService subscriptionService;

    @Inject
    public MensagemSubscriptionResource(MensagemSubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @POST
    @Path("/subscribe")
    @Transactional
    public Response subscribe(MensagemSubscription subscription) {
        try{
            subscriptionService.add(subscription);
            return ResponseUtils.ok();
        }catch (Exception e){
            return ResponseUtils.serverError();
        }
    }

    @POST
    @Path("/unsubscribe")
    @Transactional
    public Response unsubscribe(MensagemSubscription subscription) {
        try {
            subscriptionService.delete(subscription);
            return ResponseUtils.ok();
        }catch (Exception e){
            return ResponseUtils.serverError();
        }
    }

}
