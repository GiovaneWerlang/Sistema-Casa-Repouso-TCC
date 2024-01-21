package br.edu.utfpr.whatsapp;

import br.edu.utfpr.configuracaosistema.ConfiguracaoSistemaModel;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@ApplicationScoped
public class EnvioWhatsapp {

    public void enviar(String telefoneProfissional, String texto, ConfiguracaoSistemaModel configuracaoSistemaModel) {

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://graph.facebook.com/v13.0/" + configuracaoSistemaModel.getWhatsNumeroId() + "/messages"))
                    .header("Authorization", "Bearer " +  configuracaoSistemaModel.getWhatsToken())
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \" 55" + telefoneProfissional + "\", \"type\": \"text\", \"text\": { \"preview_url\": false, \"body\": \"" + texto + "\" } }"))
                    .build();
            HttpClient http = HttpClient.newHttpClient();
            HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
