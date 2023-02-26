package br.edu.utfpr.erro;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldError {

    private String message;
    private String field;
}

