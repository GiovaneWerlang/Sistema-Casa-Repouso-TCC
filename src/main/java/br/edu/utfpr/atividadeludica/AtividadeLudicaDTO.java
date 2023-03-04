package br.edu.utfpr.atividadeludica;

import br.edu.utfpr.enums.Situacao;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;

@Data
public class AtividadeLudicaDTO {

    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode ser vazio")
    @Size(max = 255, message = "Não pode ter mais de 100 caracteres")
    private String nome;

    @NotNull(message = "Não pode ser nulo")
    private OffsetDateTime dataHora;

    @NotNull(message = "Não pode ser nulo")
    private Situacao situacao;

}
