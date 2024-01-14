package br.edu.utfpr.atividadesresidente.processa;

import java.util.List;

public interface ProcessaAtividade<T> {

    void processarLista(List<String> emails, List<String> telefones);

    String criaCorpo(T model);

    List<T> buscarAtividadesPendentes();

    void enviarEmails(List<String> emails, String corpo, T model);
    void enviarWhatsapps(List<String> telefones, String corpo, T model);

    void atualizarSituacaoDaAtividade(Long id);

}
