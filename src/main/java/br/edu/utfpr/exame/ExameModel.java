package br.edu.utfpr.exame;

import br.edu.utfpr.especialidade.EspecialidadeModel;
import br.edu.utfpr.profissional.ProfissionalModel;
import br.edu.utfpr.residente.ResidenteModel;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "exame")
public class ExameModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "datahora")
    private OffsetDateTime dataHora;

    @Column(name = "local")
    private String local;

    @Column(name = "laudo")
    private String laudo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idespecialidade")
    private EspecialidadeModel especialidade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idprofissional")
    private ProfissionalModel profissional;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idresidente")
    private ResidenteModel residente;
}
