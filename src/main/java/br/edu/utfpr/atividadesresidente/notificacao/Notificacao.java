package br.edu.utfpr.atividadesresidente.notificacao;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Notificacao {

    public String title;
    public DadosNotificacao data;
    public List<AcoesNotificacao> actions;

}
