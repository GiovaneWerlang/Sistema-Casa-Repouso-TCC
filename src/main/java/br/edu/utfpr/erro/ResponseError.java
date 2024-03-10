package br.edu.utfpr.erro;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.ConstraintViolation;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class ResponseError {

    private String message;
    private Collection<FieldError> errors;

    public Response returnWithStatusCode(int code){
        return Response.status(code).entity(this).build();
    }

    public static ResponseError createFromViolations(Set<? extends ConstraintViolation<?>> violations){

        Collection<FieldError> errors = violations.stream().map(erro ->
                new FieldError(erro.getMessage(), erro.getPropertyPath().toString())).toList();

        String message = "Erro de validação de campos.";

        return new ResponseError(message, errors);
    }


}