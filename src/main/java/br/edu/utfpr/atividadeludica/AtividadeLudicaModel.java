package br.edu.utfpr.atividadeludica;

import br.edu.utfpr.enums.Situacao;
import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "atividadeludica")
public class AtividadeLudicaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nome")
    @Size(max = 255, min = 0)
    private String nome;

    @Column(name = "datahora")
    private LocalDateTime dataHora;

    @Column(name = "situacao")
    @Enumerated(EnumType.STRING)
    private Situacao situacao;

}
