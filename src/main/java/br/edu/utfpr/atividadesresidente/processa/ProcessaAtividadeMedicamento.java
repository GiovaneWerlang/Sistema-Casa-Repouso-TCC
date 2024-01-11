package br.edu.utfpr.atividadesresidente.processa;

import br.edu.utfpr.atividadesresidente.atividademedicamentoresidente.AtividadeMedicamentoResidenteModel;
import br.edu.utfpr.atividadesresidente.atividademedicamentoresidente.AtividadeMedicamentoResidenteRepository;
import br.edu.utfpr.email.EnvioEmail;

import java.util.List;

public class ProcessaAtividadeMedicamento implements ProcessaAtividade<AtividadeMedicamentoResidenteModel>{


    private AtividadeMedicamentoResidenteRepository atividadeMedicamentoResidenteRepository;

    public ProcessaAtividadeMedicamento(AtividadeMedicamentoResidenteRepository atividadeMedicamentoResidenteRepository) {
        this.atividadeMedicamentoResidenteRepository = atividadeMedicamentoResidenteRepository;
    }

    @Override
    public void processarLista(List<String> emails) {

        List<AtividadeMedicamentoResidenteModel> lista = buscarMedicamentosPendentes();

        for (AtividadeMedicamentoResidenteModel model : lista){
            enviarEmails(emails, criaCorpo(model), model);
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

    public  List<AtividadeMedicamentoResidenteModel> buscarMedicamentosPendentes(){
        return atividadeMedicamentoResidenteRepository.findToSendByDatahoraSituacao();
    }

    public void enviarEmails(List<String> emails, String corpo, AtividadeMedicamentoResidenteModel model){
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
        atividadeMedicamentoResidenteRepository.atualizarSituacaoEnviada(id);
    }

}
