package br.edu.utfpr.atividadesresidente.mensagemsubscription;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "mensagemsubscription")
public class MensagemSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "endpoint")
    private String endpoint;

    @Column(name = "p256dh")
    private String p256dh;

    @Column(name = "auth")
    private String auth;

}
