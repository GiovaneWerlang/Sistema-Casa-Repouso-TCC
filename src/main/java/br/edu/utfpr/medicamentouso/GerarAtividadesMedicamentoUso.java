package br.edu.utfpr.medicamentouso;

import br.edu.utfpr.atividadesresidente.atividademedicamentoresidente.AtividadeMedicamentoResidenteModel;
import br.edu.utfpr.enums.SituacaoAtividade;

import java.util.ArrayList;
import java.util.List;

public class GerarAtividadesMedicamentoUso {

    private final int HORAS24 = 24;
    private final int HORAS1 = 1;
    private final int POSICAOINICIAL = 1;

    public List<AtividadeMedicamentoResidenteModel> gerar(MedicamentoUsoModel model){
        List<AtividadeMedicamentoResidenteModel> atividades = new ArrayList<>();

        int usos = calcularUsos(model);
        int horas = calcularHoras(model);

        for (int posicao = POSICAOINICIAL; posicao <= usos; posicao++){
            AtividadeMedicamentoResidenteModel atividade = new AtividadeMedicamentoResidenteModel();
            atividade.setMedicamento(model);
            atividade.setDescricao(
                    model.getMedicamento().getNome() + " - Principio ativo: " +
                            model.getMedicamento().getPrincipioAtivo() +
                            " - Qtde: " + model.getQtdeMedicamento() +
                            " - Residente: " + model.getResidente().getNome());
            atividade.setDataHora(
                    posicao == POSICAOINICIAL ? model.getDataHoraInicio() :  model.getDataHoraInicio().plusHours(horas * posicao)
            );
            atividade.setSituacao(SituacaoAtividade.PENDENTE);
            atividades.add(atividade);
        }
        return atividades;
    }

    public int calcularUsos(MedicamentoUsoModel model){
        return model.getQtdeDiasUso() * model.getQtdeVezesAoDia();
    }

    public int calcularHoras(MedicamentoUsoModel model){
        if(model.getIntervalo() < HORAS24){
            if(model.getQtdeVezesAoDia() != HORAS1){
                return HORAS24 / model.getQtdeVezesAoDia();
            }else{
                return HORAS1;
            }
        }else{
            if(model.getQtdeVezesAoDia() != HORAS1){
                return model.getIntervalo();
            }else{
                return HORAS1;
            }
        }
   }

}
