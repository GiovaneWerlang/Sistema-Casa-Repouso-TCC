package br.edu.utfpr.entradasaida;

import br.edu.utfpr.residente.ResidenteModel;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "entradasaida")
public class EntradaSaidaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "datahorasaida")
    private OffsetDateTime dataHoraSaida;

    @Column(name = "datahoraentrada")
    private OffsetDateTime dataHoraEntrada;

    @Column(name = "descricao")
    private String descricao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idresidente")
    private ResidenteModel residente;
}
