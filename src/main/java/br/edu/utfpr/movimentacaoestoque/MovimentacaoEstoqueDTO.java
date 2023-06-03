package br.edu.utfpr.movimentacaoestoque;

import br.edu.utfpr.enums.TipoMovimentacao;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MovimentacaoEstoqueDTO {

    @Max(value = 2147483647, message = "Não pode ser maior que 2147483647")
    @NotNull(message = "Não pode ser nulo")
    private Integer qtde;

    @NotBlank(message = "Não pode ser vazio")
    @NotNull(message = "Não pode ser nulo")
    private TipoMovimentacao tipo;

    @NotNull(message = "Não pode ser nulo")
    private Long medicamento;

}
