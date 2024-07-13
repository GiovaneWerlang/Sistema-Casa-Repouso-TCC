package br.edu.utfpr.movimentacaoestoque;

import br.edu.utfpr.enums.TipoMovimentacao;
import lombok.Data;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
public class MovimentacaoEstoqueDTO {

    @Max(value = 2147483647, message = "Não pode ser maior que 2147483647")
    @Min(value = 1, message = "Não pode ser menor que 1")
    @NotNull(message = "Não pode ser nulo")
    private Integer qtde;

    @NotNull(message = "Não pode ser nulo")
    private TipoMovimentacao tipo;

    @NotNull(message = "Não pode ser nulo")
    private Long medicamento;

}
