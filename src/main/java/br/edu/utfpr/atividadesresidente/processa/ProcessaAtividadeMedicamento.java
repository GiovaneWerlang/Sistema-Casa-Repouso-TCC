package br.edu.utfpr.atividadesresidente.processa;

import br.edu.utfpr.atividadesresidente.atividademedicamentoresidente.AtividadeMedicamentoResidenteModel;
import br.edu.utfpr.atividadesresidente.atividademedicamentoresidente.AtividadeMedicamentoResidenteRepository;
import br.edu.utfpr.email.EnvioEmail;
import br.edu.utfpr.whatsapp.EnvioWhatsapp;

import javax.enterprise.context.ApplicationScoped;
 import java.util.List;

@ApplicationScoped
public class ProcessaAtividadeMedicamento implements ProcessaAtividade<AtividadeMedicamentoResidenteModel>{

    private AtividadeMedicamentoResidenteRepository atividadeMedicamentoResidenteRepository;
    private EnvioEmail envioEmail;
    private EnvioWhatsapp envioWhatsapp;

    public ProcessaAtividadeMedicamento(AtividadeMedicamentoResidenteRepository atividadeMedicamentoResidenteRepository,
                                        EnvioEmail envioEmail,
                                        EnvioWhatsapp envioWhatsapp) {
        this.atividadeMedicamentoResidenteRepository = atividadeMedicamentoResidenteRepository;
        this.envioEmail = envioEmail;
        this.envioWhatsapp = envioWhatsapp;
    }

    @Override
    public void processarLista(List<String> emails, List<String> telefones) {

        List<AtividadeMedicamentoResidenteModel> lista = buscarAtividadesPendentes();

        for (AtividadeMedicamentoResidenteModel model : lista){
            enviarEmails(emails, criaCorpo(model), model);
            enviarWhatsapps(telefones, criaCorpo(model), model);
        }
    }

    public String criaCorpo(AtividadeMedicamentoResidenteModel model){
        StringBuilder sb = new StringBuilder();
        sb.append("Olá! Este é um lembrete de ingestão de ");
        sb.append(model.getDescricao());
        sb.append(" agendada para o dia ");
        sb.append(model.getDataHora().getDayOfMonth());
        sb.append(", às ");
        sb.append(model.getDataHora().getHour());
        sb.append(":");
        sb.append(model.getDataHora().getMinute());
        sb.append(" horas.");
        return sb.toString();
    }

    public List<AtividadeMedicamentoResidenteModel> buscarAtividadesPendentes(){
        return atividadeMedicamentoResidenteRepository.findToSendByDatahoraSituacao();
    }

    public void enviarEmails(List<String> emails, String corpo, AtividadeMedicamentoResidenteModel model){
        try {
            for (String email : emails) {
                envioEmail.enviar(email, "Lembrete de atividade", corpo);
            }
            atualizarSituacaoDaAtividade(model.getId());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void enviarWhatsapps(List<String> telefones, String corpo, AtividadeMedicamentoResidenteModel model) {
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
        atividadeMedicamentoResidenteRepository.atualizarSituacaoEnviada(id);
    }

}
