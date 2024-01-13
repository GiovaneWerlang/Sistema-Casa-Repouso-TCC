package br.edu.utfpr.consulta;

import br.edu.utfpr.atividadesresidente.atividadeconsultaresidente.AtividadeConsultaResidenteModel;
import br.edu.utfpr.enums.SituacaoAtividade;

public class GerarAtividadeConsulta {

    public AtividadeConsultaResidenteModel gerar(ConsultaModel model){
        AtividadeConsultaResidenteModel atividadeConsultaResidenteModel = new AtividadeConsultaResidenteModel();
        atividadeConsultaResidenteModel.setDescricao(model.getDescricao() +
                " - Residente: " + model.getResidente().getNome());
        atividadeConsultaResidenteModel.setDataHora(model.getDataHora());
        atividadeConsultaResidenteModel.setConsulta(model);
        atividadeConsultaResidenteModel.setSituacao(SituacaoAtividade.PENDENTE);
        return atividadeConsultaResidenteModel;
    }

}
