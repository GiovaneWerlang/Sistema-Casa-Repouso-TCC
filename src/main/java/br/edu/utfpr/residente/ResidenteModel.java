package br.edu.utfpr.residente;

import br.edu.utfpr.pessoa.PessoaModel;
import br.edu.utfpr.endereco.EnderecoModel;
import br.edu.utfpr.enums.Situacao;
import br.edu.utfpr.enums.TipoEstadia;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "residente")
public class ResidenteModel extends PessoaModel {

    @Column(name = "situacao")
    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    @Column(name = "tipoestadia")
    @Enumerated(EnumType.STRING)
    private TipoEstadia tipoEstadia;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss UTC")
    //@JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Column(name = "datahoraingresso")
    private OffsetDateTime dataHoraIngresso;

    @Column(name = "datahoraprevisaosaida")
    private OffsetDateTime dataHoraPrevisaoSaida;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "idendereco")
//    private EnderecoModel endereco;
}
