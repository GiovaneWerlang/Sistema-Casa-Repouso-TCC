package br.edu.utfpr.atividadesresidente.notificacao;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class AcaoDefault {

    private String operation;
    private String url;

}
