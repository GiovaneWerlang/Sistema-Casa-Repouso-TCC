package br.edu.utfpr.entradasaida;

import br.edu.utfpr.residente.ResidenteModel;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "entradasaida")
public class EntradaSaidaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "datahorasaida")
    private LocalDateTime dataHoraSaida;

    @Column(name = "datahoraentrada")
    private LocalDateTime dataHoraEntrada;

    @Column(name = "descricao")
    private String descricao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idresidente")
    private ResidenteModel residente;
}
