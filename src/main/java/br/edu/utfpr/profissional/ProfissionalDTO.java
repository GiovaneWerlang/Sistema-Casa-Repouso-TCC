package br.edu.utfpr.profissional;

import br.edu.utfpr.endereco.EnderecoModel;
import br.edu.utfpr.enums.Funcao;
import br.edu.utfpr.enums.Situacao;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
public class ProfissionalDTO {

    @Size(max=150, message = "Não pode ter mais de 150 caracteres")
    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode ser vazio")
    private String nome;

    @NotNull(message = "Não pode ser nulo")
    @Min(message = "Não pode ser menor que 1", value = 1)
    @Max(message = "Não pode ser maior que 140", value = 140)
    private int idade;

    @Size(max=11, message = "Não pode ter mais de 20 dígitos")
    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode ser vazio")
    @CPF(message = "Cpf deve ser válido")
    private String cpf;

    @Size( max=11, min=10, message = "Não pode ter menos de 10 e mais de 11 dígitos")
    @Pattern(regexp = "-?\\d+(\\.\\d+)?", message = "Telefone deve ter apenas números.")
    private String telefone;

    @Size(max=100, message = "Não pode ter mais de 100 caracteres")
    @Email(message = "Email deve ser válido")
    private String email;

    @NotNull(message = "Não pode ser nulo")
    private LocalDate dataAdmissao;

    @NotNull(message = "Não pode ser nulo")
    @Min(value = 0, message = "Não pode ser negativo")
    private Float salario;

    @NotNull(message = "Não pode ser nulo")
    private Situacao situacao;

    @NotNull(message = "Não pode ser nulo")
    private Funcao funcao;

    private Long especialidade;

    @NotNull(message = "Não pode ser nulo")
    private EnderecoModel endereco;
}
