package br.edu.utfpr.enums;

import lombok.Getter;

@Getter
public enum TipoEstadia {

    PADRAO("Padrão"),
    SENIOR("Sênior");

    private String descricao;

    TipoEstadia(String descricao){
        this.descricao = descricao;
    }
}
