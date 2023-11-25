package br.edu.utfpr.atividadesresidente;

import br.edu.utfpr.enums.SituacaoAtividade;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AtividadeResidenteDTO {

    @NotNull(message = "Não pode ser nulo")
    private SituacaoAtividade situacao;

    @NotNull(message = "Não pode ser nulo")
    private Long profissional;

}
