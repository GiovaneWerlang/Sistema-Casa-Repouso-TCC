package br.edu.utfpr.endereco;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Data
public class EnderecoDTO {

    @Size(max = 150, message = "Não pode ter mais de 150 caracteres")
    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode ser vazio")
    private String logradouro;

    @Size(max = 50, message = "Não pode ter mais de 50 caracteres")
    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode ser vazio")
    private String numero;

    @Size(max = 100, message = "Não pode ter mais de 100 caracteres")
    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode ser vazio")
    private String bairro;

    @Size(max = 150, message = "Não pode ter mais de 150 caracteres")
    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode ser vazio")
    private String municipio;

    @Size(max = 8, message = "Não pode ter mais de 8 caracteres")
    @Size(min = 8, message = "Não pode ter menos de 8 caracteres")
    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode ser vazio")
    private String cep;

    @Size(max = 20, message = "Não pode ter mais de 20 caracteres")
    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode ser vazio")
    private String estado;

    @Size(max = 100, message = "Não pode ter mais de 100 caracteres")
    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode ser vazio")
    private String pais;
}
