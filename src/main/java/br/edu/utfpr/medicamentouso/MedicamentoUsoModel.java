package br.edu.utfpr.medicamentouso;

import br.edu.utfpr.medicamentoestoque.MedicamentoEstoqueModel;
import br.edu.utfpr.residente.ResidenteModel;
import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "medicamentouso")
public class MedicamentoUsoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "intervalo")
    private Integer intervalo;

    @Column(name = "qtdevezesaodia")
    private Integer qtdeVezesAoDia;

    @Column(name = "datahorainicio")
    private LocalDateTime dataHoraInicio;

    @Column(name = "qtdediasuso")
    private Integer qtdeDiasUso;

    @Column(name = "qtdemedicamento")
    private Integer qtdeMedicamento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idresidente")
    private ResidenteModel residente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idmedicamento")
    private MedicamentoEstoqueModel medicamento;

}
