package br.edu.utfpr.atividadeludica;

import br.edu.utfpr.enums.Situacao;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class AtividadeLudicaDTO {

    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode ser vazio")
    @Size(max = 255, message = "Não pode ter mais de 100 caracteres")
    private String nome;

    @NotNull(message = "Não pode ser nulo")
    private LocalDateTime dataHora;

    @NotNull(message = "Não pode ser nulo")
    private Situacao situacao;

}
