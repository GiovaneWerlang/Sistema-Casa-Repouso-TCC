package br.edu.utfpr.consulta;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

@Data
public class ConsultaDTO {

    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode ser vazio")
    @Size(max = 255, message = "Não pode ter mais de 255 caracteres")
    private String descricao;

    @NotNull(message = "Não pode ser nulo")
    private OffsetDateTime dataHora;

    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode ser vazio")
    @Size(max = 100, message = "Não pode ter mais de 100 caracteres")
    private String local;

    @Size(max = 255, message = "Não pode ter mais de 255 caracteres")
    private String prescricao;

    @NotNull(message = "Não pode ser nulo")
    private Long especialidade;

    @NotNull(message = "Não pode ser nulo")
    private Long profissional;

    @NotNull(message = "Não pode ser nulo")
    private Long residente;
}
