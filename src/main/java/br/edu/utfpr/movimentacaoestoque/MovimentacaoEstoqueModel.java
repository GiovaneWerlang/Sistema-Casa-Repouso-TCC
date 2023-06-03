package br.edu.utfpr.movimentacaoestoque;

import br.edu.utfpr.enums.TipoMovimentacao;
import br.edu.utfpr.medicamentoestoque.MedicamentoEstoqueModel;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "movimentacaoestoque")
public class MovimentacaoEstoqueModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "qtde")
    private Integer qtde;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idmedicamento")
    private MedicamentoEstoqueModel medicamento;

}
