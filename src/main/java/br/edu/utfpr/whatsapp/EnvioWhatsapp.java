package br.edu.utfpr.whatsapp;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@ApplicationScoped
public class EnvioWhatsapp {

    @ConfigProperty(name = "whatsapp.habilitar", defaultValue="Não configurado!")
    boolean habilitar;

    @ConfigProperty(name = "whatsapp.numeroid", defaultValue="Não configurado!")
    String numeroid;

    @ConfigProperty(name = "whatsapp.token", defaultValue="Não configurado!")
    String token;

    public void enviar(String telefoneProfissional, String texto) {

        if(habilitar) {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI("https://graph.facebook.com/v13.0/" + numeroid + "/messages"))
                        .header("Authorization", "Bearer " + token)
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \" 55" + telefoneProfissional + "\", \"type\": \"text\", \"text\": { \"preview_url\": false, \"body\": \"" + texto + "\" } }"))
                        .build();
                HttpClient http = HttpClient.newHttpClient();
                HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (URISyntaxException | IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(habilitar);
    }
}
