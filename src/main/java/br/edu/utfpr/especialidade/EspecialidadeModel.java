package br.edu.utfpr.especialidade;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "especialidade")
public class EspecialidadeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nome")
    private String nome;
}
