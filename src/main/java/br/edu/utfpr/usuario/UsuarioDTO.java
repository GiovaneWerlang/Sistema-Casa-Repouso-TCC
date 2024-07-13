package br.edu.utfpr.usuario;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class UsuarioDTO {

    @Size(max=50, message = "Não pode ter mais de 50 caracteres")
    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode ser vazio")
    private String login;

    @Size(min=6, message = "Não pode ter menos de 6 caracteres")
    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode ser vazio")
    private String senha;

    @NotNull(message = "Não pode ser nulo")
    private Long profissional;
}
