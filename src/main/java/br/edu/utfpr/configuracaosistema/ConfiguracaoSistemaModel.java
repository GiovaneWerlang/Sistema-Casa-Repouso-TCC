package br.edu.utfpr.configuracaosistema;

import lombok.Data;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;

@Data
@Entity
@ApplicationScoped
@Table(name = "configuracaosistema")
public class ConfiguracaoSistemaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "habilitarenvioemail")
    private boolean habilitarEnvioEmail;

    @Column(name = "emaillogin")
    private String emailLogin;

    @Column(name = "emailsenha")
    private String emailSenha;

    @Column(name = "habilitarenviowhats")
    private boolean habilitarEnvioWhats;

    @Column(name = "whatsnumeroid")
    private String whatsNumeroId;

    @Column(name = "whatstoken")
    private String whatsToken;

}
