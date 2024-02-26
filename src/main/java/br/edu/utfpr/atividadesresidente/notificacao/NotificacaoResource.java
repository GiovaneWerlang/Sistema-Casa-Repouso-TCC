package br.edu.utfpr.atividadesresidente.notificacao;

import br.edu.utfpr.atividadesresidente.mensagemsubscription.MensagemSubscription;
import br.edu.utfpr.atividadesresidente.mensagemsubscription.MensagemSubscriptionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Security;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jose4j.lang.JoseException;

@Path("/notificacoes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Notificações")
public class NotificacaoResource {

    private NotificacaoService notificacaoService;
    private MensagemSubscriptionService subscriptionService;

    private static PushService pushService = new PushService();

    private ObjectMapper objectMapper;

    @Inject
    public NotificacaoResource(NotificacaoService notificacaoService,
                               MensagemSubscriptionService subscriptionService,
                               ObjectMapper objectMapper
    ) {
        this.notificacaoService = notificacaoService;
        this.subscriptionService = subscriptionService;
        this.objectMapper = objectMapper;
    }

    @GET
    @Path("/notificar")
    public void notificarTodos() {

        List<MensagemNotificacao> mensagens = this.notificacaoService.getMensagens();
        List<MensagemSubscription> subscriptions = this.subscriptionService.buscarTodos();

        try {
            if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
                Security.addProvider(new BouncyCastleProvider());
            }
            pushService.setPublicKey("BPtfmbAFQwggTeQKEH5pGLcP9qAC7ATbpvbxKTaIPaHVMhIoM4U_z-e-gDxIHiSUcEd41SVu-kq99frpGSTbBo0");
            pushService.setPrivateKey("RLM5YVZ2Co9a4eh8EorZRX-BKlaSsLLBjd_s2fdf2a4");
            for(MensagemNotificacao mensagem : mensagens) {
                for (MensagemSubscription subscription : subscriptions) {

                    Notification notification = new Notification(
                            subscription.getEndpoint(),
                            subscription.getP256dh(),
                            subscription.getAuth(),
                            objectMapper.writeValueAsBytes(mensagem));

                    pushService.send(notification);
                }
            }
        } catch (GeneralSecurityException | IOException | JoseException | ExecutionException
                | InterruptedException e){
            e.printStackTrace();
        }
    }

}
