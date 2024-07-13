package br.edu.utfpr.atividadesresidente;

import br.edu.utfpr.enums.SituacaoAtividade;
import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class AtividadeResidenteModel {

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

}
