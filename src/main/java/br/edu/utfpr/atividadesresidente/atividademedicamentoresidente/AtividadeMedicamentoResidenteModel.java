package br.edu.utfpr.atividadesresidente.atividademedicamentoresidente;

import br.edu.utfpr.atividadesresidente.AtividadeResidenteModel;
import br.edu.utfpr.medicamentouso.MedicamentoUsoModel;
import br.edu.utfpr.profissional.ProfissionalModel;
import lombok.Data;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
@Entity
@Table(name = "atividademedicamentoresidente")
public class AtividadeMedicamentoResidenteModel extends AtividadeResidenteModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idprofissional")
    private ProfissionalModel profissional;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idmedicamento")
    private MedicamentoUsoModel medicamento;

}
