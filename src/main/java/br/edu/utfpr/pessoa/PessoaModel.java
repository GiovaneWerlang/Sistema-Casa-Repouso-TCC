package br.edu.utfpr.pessoa;

import br.edu.utfpr.endereco.EnderecoModel;
import lombok.Data;
import jakarta.persistence.*;

@Data
@MappedSuperclass
public class PessoaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "idade")
    private int idade;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idendereco")
    private EnderecoModel endereco;
}
