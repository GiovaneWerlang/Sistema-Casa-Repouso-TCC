package br.edu.utfpr.profissional;

import br.edu.utfpr.endereco.EnderecoModel;
import br.edu.utfpr.enums.Funcao;
import br.edu.utfpr.enums.Situacao;
import br.edu.utfpr.especialidade.EspecialidadeModel;
import br.edu.utfpr.pessoa.PessoaModel;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "profissional")
public class ProfissionalModel extends PessoaModel {

    @Column(name = "dataadmissao")
    private LocalDate dataAdmissao;

    @Column(name = "salario")
    private Float salario;

    @Column(name = "situacao")
    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    @Column(name = "funcao")
    @Enumerated(EnumType.STRING)
    private Funcao funcao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idespecialidade")
    private EspecialidadeModel especialidade;

}
