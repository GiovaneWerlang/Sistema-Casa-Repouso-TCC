package br.edu.utfpr.configuracaosistema;

import lombok.Data;

import jakarta.validation.constraints.Size;

@Data
public class ConfiguracaoSistemaDTO {

    private boolean habilitarEnvioEmail;

    @Size(max = 255, message = "Não pode ter mais de 255 caracteres")
    private String emailLogin;

    @Size(max = 255, message = "Não pode ter mais de 255 caracteres")
    private String emailSenha;

    private boolean habilitarEnvioWhats;

    @Size(max = 255, message = "Não pode ter mais de 255 caracteres")
    private String whatsNumeroId;

    @Size(max = 255, message = "Não pode ter mais de 255 caracteres")
    private String whatsToken;

}
