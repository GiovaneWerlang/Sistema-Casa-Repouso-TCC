package br.edu.utfpr.enums;

import lombok.Getter;

@Getter
public enum Situacao {

    ATIVO("Ativo"),
    INATIVO("Inativo");

    private String descricao;

    Situacao(String descricao){
        this.descricao = descricao;
    }

}
