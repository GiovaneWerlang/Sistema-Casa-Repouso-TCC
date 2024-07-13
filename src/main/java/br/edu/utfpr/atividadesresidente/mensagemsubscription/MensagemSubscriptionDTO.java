package br.edu.utfpr.atividadesresidente.mensagemsubscription;

import lombok.Data;

import jakarta.persistence.Column;

@Data
public class MensagemSubscriptionDTO {

    @Column(name = "endpoint")
    private String endpoint;

    @Column(name = "p256dh")
    private String p256dh;

    @Column(name = "auth")
    private String auth;

}
