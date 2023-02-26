package br.edu.utfpr.medicamentoestoque;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class MedicamentoEstoqueDTO {

    @Size(max = 100, message = "Não pode ter mais de 100 caracteres")
    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode ser vazio")
    private String nome;

    @Size(max = 255, message = "Não pode ter mais de 255 caracteres")
    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode ser vazio")
    private String principioAtivo;

    @Max(value = 2147483647, message = "Não pode ser maior que 2147483647")
    @NotNull(message = "Não pode ser nulo")
    private Integer qtde;
}
