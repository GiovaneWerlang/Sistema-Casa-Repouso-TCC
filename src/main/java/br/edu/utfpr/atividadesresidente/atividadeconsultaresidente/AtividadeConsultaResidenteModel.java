package br.edu.utfpr.atividadesresidente.atividadeconsultaresidente;

import br.edu.utfpr.consulta.ConsultaModel;
import br.edu.utfpr.enums.SituacaoAtividade;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "atividadeconsultaresidente")
public class AtividadeConsultaResidenteModel {

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
    @JoinColumn(name = "idconsulta")
    private ConsultaModel consulta;
}
