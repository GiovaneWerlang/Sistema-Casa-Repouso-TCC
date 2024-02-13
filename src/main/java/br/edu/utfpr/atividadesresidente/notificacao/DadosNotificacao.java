package br.edu.utfpr.atividadesresidente.notificacao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class DadosNotificacao {

    @JsonProperty("onActionClick")
    public AcaoClickNotificacao onActionClick;

}
