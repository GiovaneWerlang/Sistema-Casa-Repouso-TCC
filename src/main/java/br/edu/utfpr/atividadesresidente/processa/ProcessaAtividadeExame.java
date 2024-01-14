package br.edu.utfpr.atividadesresidente.processa;

import br.edu.utfpr.atividadesresidente.atividadeexameresidente.AtividadeExameResidenteModel;
import br.edu.utfpr.atividadesresidente.atividadeexameresidente.AtividadeExameResidenteRepository;
import br.edu.utfpr.email.EnvioEmail;
import br.edu.utfpr.whatsapp.EnvioWhatsapp;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ProcessaAtividadeExame implements ProcessaAtividade<AtividadeExameResidenteModel>{

    private AtividadeExameResidenteRepository atividadeExameResidenteRepository;
    private EnvioEmail envioEmail;
    private EnvioWhatsapp envioWhatsapp;

    @Inject
    public ProcessaAtividadeExame(AtividadeExameResidenteRepository atividadeExameResidenteRepository,
                                  EnvioEmail envioEmail,
                                  EnvioWhatsapp envioWhatsapp) {
        this.atividadeExameResidenteRepository = atividadeExameResidenteRepository;
        this.envioEmail = envioEmail;
        this.envioWhatsapp = envioWhatsapp;
    }

    @Override
    public void processarLista(List<String> emails, List<String> telefones) {

        List<AtividadeExameResidenteModel> lista = buscarAtividadesPendentes();;

        for (AtividadeExameResidenteModel model : lista){
            enviarEmails(emails, criaCorpo(model), model);
            enviarWhatsapps(telefones, criaCorpo(model), model);
        }
    }

    public String criaCorpo(AtividadeExameResidenteModel model){
        StringBuilder sb = new StringBuilder();
        sb.append("Olá! Este é um lembrete de exame ");
        sb.append(model.getDescricao());
        sb.append(" agendado para o dia ");
        sb.append(model.getDataHora().getDayOfMonth());
        sb.append(", às ");
        sb.append(model.getDataHora().getHour());
        sb.append(":");
        sb.append(model.getDataHora().getMinute());
        sb.append(" horas. No local: ");
        sb.append(model.getExame().getLocal());
        return sb.toString();
    }

    public List<AtividadeExameResidenteModel> buscarAtividadesPendentes(){
        return atividadeExameResidenteRepository.findToSendByDatahoraSituacao();
    }

    public void enviarEmails(List<String> emails, String corpo, AtividadeExameResidenteModel model){
        try {
            for (String email : emails) {
                envioEmail.enviar(email, "Lembrete de atividade", corpo);
            }
            atualizarSituacaoDaAtividade(model.getId());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void enviarWhatsapps(List<String> telefones, String corpo, AtividadeExameResidenteModel model) {
        try {
            for (String telefone : telefones) {
                envioWhatsapp.enviar(telefone,corpo);
            }
            atualizarSituacaoDaAtividade(model.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizarSituacaoDaAtividade(Long id){
       atividadeExameResidenteRepository.atualizarSituacaoEnviada(id);
    }

}
