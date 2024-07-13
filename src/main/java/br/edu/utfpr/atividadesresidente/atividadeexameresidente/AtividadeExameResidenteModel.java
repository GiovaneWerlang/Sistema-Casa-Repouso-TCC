package br.edu.utfpr.atividadesresidente.atividadeexameresidente;

import br.edu.utfpr.atividadesresidente.AtividadeResidenteModel;
import br.edu.utfpr.exame.ExameModel;
import br.edu.utfpr.profissional.ProfissionalModel;
import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "atividadeexameresidente")
public class AtividadeExameResidenteModel extends AtividadeResidenteModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idexame")
    private ExameModel exame;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idprofissional")
    private ProfissionalModel profissional;

}
