package br.edu.utfpr.atividadesresidente.processa;

import br.edu.utfpr.atividadesresidente.AtividadeResidenteModel;
import br.edu.utfpr.configuracaosistema.ConfiguracaoSistemaModel;
import br.edu.utfpr.crud.CrudRepositoryAtividade;
import br.edu.utfpr.email.EnvioEmail;
import br.edu.utfpr.whatsapp.EnvioWhatsapp;

import java.util.List;

public abstract class ProcessaAtividade<T extends AtividadeResidenteModel, R extends CrudRepositoryAtividade<T>>{
    private R repository;
    private ConfiguracaoSistemaModel configuracaoSistema;
    private EnvioEmail envioEmail;
    private EnvioWhatsapp envioWhatsapp;

    protected ProcessaAtividade(R repository,
                             ConfiguracaoSistemaModel configuracaoSistema,
                             EnvioEmail envioEmail,
                             EnvioWhatsapp envioWhatsapp) {
        this.repository = repository;
        this.configuracaoSistema = configuracaoSistema;
        this.envioEmail = envioEmail;
        this.envioWhatsapp = envioWhatsapp;
    }

    public void processarLista(List<String> emails, List<String> telefones) {

        List<T> lista = buscarAtividadesPendentes();

        for (T model : lista){
            if(configuracaoSistema.isHabilitarEnvioEmail()) {
                enviarEmails(emails, criaCorpo(model), model);
            }
            if(configuracaoSistema.isHabilitarEnvioWhats()) {
                enviarWhatsapps(telefones, criaCorpo(model), model);
            }
        }
    }

    public abstract String criaCorpo(T model);

    public List<T> buscarAtividadesPendentes(){
        return repository.findToSendByDatahoraSituacao();
    }

    public void enviarEmails(List<String> emails, String corpo, T model){
        try {
            for (String email : emails) {
                envioEmail.enviar(email, "Lembrete de atividade", corpo, configuracaoSistema);
            }
            atualizarSituacaoDaAtividade(model.getId());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void enviarWhatsapps(List<String> telefones, String corpo, T model) {
        try {
            for (String telefone : telefones) {
                envioWhatsapp.enviar(telefone,corpo, configuracaoSistema);
            }
            atualizarSituacaoDaAtividade(model.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizarSituacaoDaAtividade(Long id){
        repository.atualizarSituacaoEnviada(id);
    }
}
