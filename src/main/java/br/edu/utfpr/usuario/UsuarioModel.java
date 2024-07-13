package br.edu.utfpr.usuario;

import br.edu.utfpr.profissional.ProfissionalModel;
import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Data
@Entity
@Table(name = "usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "login")
    @Size(max = 50, min = 0)
    private String login;

    @Column(name = "senha")
    @Size(min = 6)
    private String senha;

    @OneToOne()
    @JoinColumn(name = "idprofissional")
    private ProfissionalModel profissional;
}
