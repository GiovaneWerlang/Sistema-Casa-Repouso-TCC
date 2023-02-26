package br.edu.utfpr.medicamentoestoque;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "medicamentoestoque")
public class MedicamentoEstoqueModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "principioativo")
    private String principioAtivo;

    @Column(name = "qtde")
    private Integer qtde;

}
