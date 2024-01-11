package br.edu.utfpr.atividadesresidente.processa;

import br.edu.utfpr.atividadesresidente.atividadeexameresidente.AtividadeExameResidenteModel;
import br.edu.utfpr.atividadesresidente.atividadeexameresidente.AtividadeExameResidenteRepository;
import br.edu.utfpr.email.EnvioEmail;

import javax.inject.Inject;
import java.util.List;

public class ProcessaAtividadeExame implements ProcessaAtividade<AtividadeExameResidenteModel>{

    private AtividadeExameResidenteRepository atividadeExameResidenteRepository;

    @Inject
    public ProcessaAtividadeExame(AtividadeExameResidenteRepository atividadeExameResidenteRepository) {
        this.atividadeExameResidenteRepository = atividadeExameResidenteRepository;
    }

    @Override
    public void processarLista(List<String> emails) {

        List<AtividadeExameResidenteModel> lista = buscarExamesPendentes();;

        for (AtividadeExameResidenteModel model : lista){
            enviarEmails(emails, criaCorpo(model), model);
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

    public  List<AtividadeExameResidenteModel> buscarExamesPendentes(){
        return atividadeExameResidenteRepository.findToSendByDatahoraSituacao();
    }

    public void enviarEmails(List<String> emails, String corpo, AtividadeExameResidenteModel model){
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
       atividadeExameResidenteRepository.atualizarSituacaoEnviada(id);
    }

}
