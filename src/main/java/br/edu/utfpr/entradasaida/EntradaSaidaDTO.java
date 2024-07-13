package br.edu.utfpr.entradasaida;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class EntradaSaidaDTO {

    private LocalDateTime dataHoraSaida;

    private LocalDateTime dataHoraEntrada;

    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode ser vazio")
    @Size(max = 255, message = "Não pode ter mais de 255 caracteres")
    private String descricao;

    @NotNull(message = "Não pode ser nulo")
    private Long residente;
}
