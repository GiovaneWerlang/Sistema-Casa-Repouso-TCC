package br.edu.utfpr.atividadesresidente.processa;

import br.edu.utfpr.atividadesresidente.atividadeconsultaresidente.AtividadeConsultaResidenteModel;
import br.edu.utfpr.atividadesresidente.atividadeconsultaresidente.AtividadeConsultaResidenteRepository;
import br.edu.utfpr.email.EnvioEmail;
import br.edu.utfpr.whatsapp.EnvioWhatsapp;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ProcessaAtividadeConsulta implements ProcessaAtividade<AtividadeConsultaResidenteModel>{

    private AtividadeConsultaResidenteRepository atividadeConsultaResidenteRepository;
    private EnvioEmail envioEmail;
    private EnvioWhatsapp envioWhatsapp;

    @Inject
    public ProcessaAtividadeConsulta(AtividadeConsultaResidenteRepository atividadeConsultaResidenteRepository,
                                     EnvioEmail envioEmail,
                                     EnvioWhatsapp envioWhatsapp) {
        this.atividadeConsultaResidenteRepository = atividadeConsultaResidenteRepository;
        this.envioEmail = envioEmail;
        this.envioWhatsapp = envioWhatsapp;
    }

    @Override
    public void processarLista(List<String> emails, List<String> telefones) {

        List<AtividadeConsultaResidenteModel> lista = buscarAtividadesPendentes();

        for (AtividadeConsultaResidenteModel model : lista){
            enviarEmails(emails, criaCorpo(model), model);
            enviarWhatsapps(telefones, criaCorpo(model), model);
        }
    }

    public String criaCorpo(AtividadeConsultaResidenteModel model){
        StringBuilder sb = new StringBuilder();
        sb.append("Olá! Este é um lembrete de consulta ");
        sb.append(model.getDescricao());
        sb.append(" agendada para o dia ");
        sb.append(model.getDataHora().getDayOfMonth());
        sb.append(", às ");
        sb.append(model.getDataHora().getHour());
        sb.append(":");
        sb.append(model.getDataHora().getMinute());
        sb.append(" horas. No local: ");
        sb.append(model.getConsulta().getLocal());
        return sb.toString();
    }

    public  List<AtividadeConsultaResidenteModel> buscarAtividadesPendentes(){
        return atividadeConsultaResidenteRepository.findToSendByDatahoraSituacao();
    }

    public void enviarEmails(List<String> emails, String corpo, AtividadeConsultaResidenteModel model){
        try {
            for (String email : emails) {
                envioEmail.enviar(email, "Lembrete de atividade", corpo);
            }
            atualizarSituacaoDaAtividade(model.getId());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void enviarWhatsapps(List<String> telefones, String corpo, AtividadeConsultaResidenteModel model) {
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
       atividadeConsultaResidenteRepository.atualizarSituacaoEnviada(id);
    }

}
