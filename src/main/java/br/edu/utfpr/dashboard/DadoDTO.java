package br.edu.utfpr.dashboard;

import br.edu.utfpr.enums.Funcao;
import br.edu.utfpr.enums.SituacaoAtividade;
import br.edu.utfpr.enums.TipoEstadia;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@RegisterForReflection
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadoDTO {
    private String label;
    private Long data;

    public DadoDTO(Funcao label, Long data) {
        this.label = label.getDescricao();
        this.data = data;
    }

    public DadoDTO(TipoEstadia label, Long data) {
        this.label = label.getDescricao();
        this.data = data;
    }

    public DadoDTO(SituacaoAtividade label, Long data) {
        this.label = label.getDescricao();
        this.data = data;
    }

    public DadoDTO(String label, Integer data) {
        this.label = label;
        this.data = data.longValue();
    }

}
