package br.edu.utfpr.exame;

import br.edu.utfpr.atividadesresidente.atividadeexameresidente.AtividadeExameResidenteModel;
import br.edu.utfpr.enums.SituacaoAtividade;

public class GerarAtividadeExame {

    public AtividadeExameResidenteModel gerar(ExameModel model){
        AtividadeExameResidenteModel atividadeExameResidenteModel = new AtividadeExameResidenteModel();
        atividadeExameResidenteModel.setDescricao(model.getDescricao() +
                " - Residente: " + model.getResidente().getNome());
        atividadeExameResidenteModel.setDataHora(model.getDataHora());
        atividadeExameResidenteModel.setExame(model);
        atividadeExameResidenteModel.setSituacao(SituacaoAtividade.PENDENTE);
        return atividadeExameResidenteModel;
    }

}
