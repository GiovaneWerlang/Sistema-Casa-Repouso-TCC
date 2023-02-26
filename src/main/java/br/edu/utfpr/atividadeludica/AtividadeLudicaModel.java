package br.edu.utfpr.atividadeludica;

import br.edu.utfpr.enums.Situacao;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

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

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "situacao")
    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    @Column(name = "hora")
    private LocalTime hora;
}
