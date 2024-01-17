package br.edu.utfpr.atividadesresidente.atividadeconsultaresidente;

import br.edu.utfpr.atividadesresidente.AtividadeResidenteModel;
import br.edu.utfpr.consulta.ConsultaModel;
import br.edu.utfpr.profissional.ProfissionalModel;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "atividadeconsultaresidente")
public class AtividadeConsultaResidenteModel extends AtividadeResidenteModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idconsulta")
    private ConsultaModel consulta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idprofissional")
    private ProfissionalModel profissional;

}
