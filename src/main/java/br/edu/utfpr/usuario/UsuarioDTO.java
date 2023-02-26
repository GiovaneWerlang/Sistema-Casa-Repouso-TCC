package br.edu.utfpr.usuario;

import br.edu.utfpr.profissional.ProfissionalModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UsuarioDTO {

    @Size(max=50, message = "Não pode ter mais de 50 caracteres")
    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode ser vazio")
    private String login;

    @Size(max=50, message = "Não pode ter mais de 50 caracteres")
    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode ser vazio")
    private String senha;

    @NotNull(message = "Não pode ser nulo")
    private Long profissional;
}
