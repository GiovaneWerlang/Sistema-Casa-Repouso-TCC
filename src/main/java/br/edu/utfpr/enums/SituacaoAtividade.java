package br.edu.utfpr.enums;

import lombok.Getter;

@Getter
public enum SituacaoAtividade {

    REALIZADA("Realizada"),
    NAOREALIZADA("Não Realizada");

    private String descricao;

    SituacaoAtividade(String descricao){
        this.descricao = descricao;
    }

}
