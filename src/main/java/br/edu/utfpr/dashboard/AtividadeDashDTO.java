package br.edu.utfpr.dashboard;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@RegisterForReflection
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtividadeDashDTO {

    private Long id;
    private String descricao;
    private LocalDateTime dataHora;
    private String tipo;

}
