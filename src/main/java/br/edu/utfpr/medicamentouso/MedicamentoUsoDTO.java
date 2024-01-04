package br.edu.utfpr.medicamentouso;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class MedicamentoUsoDTO {

    @Max(value = 2147483647, message = "Não pode ser maior que 2147483647")
    @Min(value = 1, message = "Não pode ser menor que 1")
    @NotNull(message = "Não pode ser nulo")
    private Integer intervalo;

    @Max(value = 24, message = "Não pode ser maior que 24")
    @Min(value = 1, message = "Não pode ser menor que 1")
    @NotNull(message = "Não pode ser nulo")
    private Integer qtdeVezesAoDia;

    @NotNull(message = "Não pode ser nulo")
    private LocalDateTime dataHoraInicio;

    @Max(value = 2147483647, message = "Não pode ser maior que 2147483647")
    @Min(value = 1, message = "Não pode ser menor que 1")
    @NotNull(message = "Não pode ser nulo")
    private Integer qtdeDiasUso;

    @Max(value = 2147483647, message = "Não pode ser maior que 2147483647")
    @Min(value = 1, message = "Não pode ser menor que 1")
    @NotNull(message = "Não pode ser nulo")
    private Integer qtdeMedicamento;

    @NotNull(message = "Não pode ser nulo")
    private Long residente;

    @NotNull(message = "Não pode ser nulo")
    private Long medicamento;

}
