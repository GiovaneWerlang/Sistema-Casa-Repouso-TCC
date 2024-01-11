package br.edu.utfpr.atividadesresidente.processa;

import br.edu.utfpr.atividadesresidente.atividadeconsultaresidente.AtividadeConsultaResidenteModel;
import br.edu.utfpr.atividadesresidente.atividadeconsultaresidente.AtividadeConsultaResidenteRepository;
import br.edu.utfpr.email.EnvioEmail;

import javax.inject.Inject;
import java.util.List;

public class ProcessaAtividadeConsulta implements ProcessaAtividade<AtividadeConsultaResidenteModel>{

    private AtividadeConsultaResidenteRepository atividadeConsultaResidenteRepository;

    @Inject
    public ProcessaAtividadeConsulta(AtividadeConsultaResidenteRepository atividadeConsultaResidenteRepository) {
        this.atividadeConsultaResidenteRepository = atividadeConsultaResidenteRepository;
    }

    @Override
    public void processarLista(List<String> emails) {

        List<AtividadeConsultaResidenteModel> lista = buscarConsultasPendentes();

        for (AtividadeConsultaResidenteModel model : lista){
            enviarEmails(emails, criaCorpo(model), model);
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

    public  List<AtividadeConsultaResidenteModel> buscarConsultasPendentes(){
        return atividadeConsultaResidenteRepository.findToSendByDatahoraSituacao();
    }

    public void enviarEmails(List<String> emails, String corpo, AtividadeConsultaResidenteModel model){
        try {
            for (String email : emails) {
                EnvioEmail envioEmail = new EnvioEmail();
                envioEmail.enviar(email, "Lembrete de atividade", corpo);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        atualizarSituacaoDaAtividade(model.getId());
    }

    public void atualizarSituacaoDaAtividade(Long id){
       atividadeConsultaResidenteRepository.atualizarSituacaoEnviada(id);
    }

}
