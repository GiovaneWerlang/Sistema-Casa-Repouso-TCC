package br.edu.utfpr.enums;

import lombok.Getter;

@Getter
public enum Funcao {

    CUIDADOR("Cuidador"),
    ENFERMEIRO("Enfermeiro"),
    FUNCIONARIO("Funcionário"),
    MEDICO("Médico"),
    VOLUNTARIO("Voluntário");

    private String descricao;

    Funcao(String descricao){
        this.descricao = descricao;
    }
}
