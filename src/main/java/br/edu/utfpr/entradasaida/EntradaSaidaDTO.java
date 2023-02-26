package br.edu.utfpr.entradasaida;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

@Data
public class EntradaSaidaDTO {

    private OffsetDateTime dataHoraSaida;

    private OffsetDateTime dataHoraEntrada;

    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode ser vazio")
    @Size(max = 255, message = "Não pode ter mais de 255 caracteres")
    private String descricao;

    @NotNull(message = "Não pode ser nulo")
    private Long residente;
}
