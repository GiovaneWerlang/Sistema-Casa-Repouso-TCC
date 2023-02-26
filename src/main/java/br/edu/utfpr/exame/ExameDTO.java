package br.edu.utfpr.exame;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

@Data
public class ExameDTO {

    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode ser vazio")
    @Size(max = 255, message = "Não pode ter mais de 255 caracteres")
    private String nome;

    @NotNull(message = "Não pode ser nulo")
    private OffsetDateTime dataHora;

    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode ser vazio")
    @Size(max = 100, message = "Não pode ter mais de 100 caracteres")
    private String local;

    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode ser vazio")
    @Size(max = 255, message = "Não pode ter mais de 255 caracteres")
    private String laudo;

    @NotNull(message = "Não pode ser nulo")
    private Long especialidade;

    @NotNull(message = "Não pode ser nulo")
    private Long profissional;

    @NotNull(message = "Não pode ser nulo")
    private Long residente;
}
