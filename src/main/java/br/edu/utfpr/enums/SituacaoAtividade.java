package br.edu.utfpr.enums;

import lombok.Getter;

@Getter
public enum SituacaoAtividade {

    REALIZADA("Realizada"),
    PENDENTE("Pendente"),
    ENVIADA("Enviada"),
    NAOREALIZADA("Não Realizada");

    private String descricao;

    SituacaoAtividade(String descricao){
        this.descricao = descricao;
    }

}
