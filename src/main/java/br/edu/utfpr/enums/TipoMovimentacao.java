package br.edu.utfpr.enums;

import lombok.Getter;

@Getter
public enum TipoMovimentacao {

    ENTRADA("Entrada"),
    SAIDA("Saída");

    private String descricao;

    TipoMovimentacao(String descricao) {
        this.descricao = descricao;
    }
}
