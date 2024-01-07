package br.edu.utfpr.atividadesresidente.atividadeexameresidente;

import br.edu.utfpr.enums.SituacaoAtividade;
import br.edu.utfpr.exame.ExameModel;
import br.edu.utfpr.profissional.ProfissionalModel;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "atividadeexameresidente")
public class AtividadeExameResidenteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "datahora")
    private LocalDateTime dataHora;

    @Column(name = "situacaoatividade")
    @Enumerated(EnumType.STRING)
    private SituacaoAtividade situacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idexame")
    private ExameModel exame;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idprofissional")
    private ProfissionalModel profissional;

}
