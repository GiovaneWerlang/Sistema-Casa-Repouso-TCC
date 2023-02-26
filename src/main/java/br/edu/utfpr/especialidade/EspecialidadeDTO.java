package br.edu.utfpr.especialidade;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class EspecialidadeDTO {

    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode ser vazio")
    @Size(max = 100, message = "Não pode ter mais de 100 caracteres")
    private String nome;
}
