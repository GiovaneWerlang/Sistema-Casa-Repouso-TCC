package br.edu.utfpr.usuario;

import br.edu.utfpr.enums.Funcao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class UsuarioDados {

    private Long id;
    private String nome;
    private String token;
    private Funcao funcao;
    private LocalDateTime dataHoraExpiracao;

}
