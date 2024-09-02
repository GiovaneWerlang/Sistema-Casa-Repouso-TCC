package br.edu.utfpr.residente;

import br.edu.utfpr.enums.Situacao;
import br.edu.utfpr.enums.TipoEstadia;
import br.edu.utfpr.pessoa.PessoaModel;
import lombok.Data;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper=false)
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

    @Column(name = "datahoraingresso")
    private LocalDateTime dataHoraIngresso;

    @Column(name = "datahoraprevisaosaida")
    private LocalDateTime dataHoraPrevisaoSaida;

}
