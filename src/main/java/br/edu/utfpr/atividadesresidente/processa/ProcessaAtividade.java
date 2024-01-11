package br.edu.utfpr.atividadesresidente.processa;

import java.util.List;

public interface ProcessaAtividade<T> {

    void processarLista(List<String> emails);

    String criaCorpo(T model);

    void enviarEmails(List<String> emails, String corpo, T model);

}
