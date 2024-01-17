package br.edu.utfpr.atividadesresidente.processa;

import br.edu.utfpr.atividadesresidente.atividademedicamentoresidente.AtividadeMedicamentoResidenteModel;
import br.edu.utfpr.atividadesresidente.atividademedicamentoresidente.AtividadeMedicamentoResidenteRepository;
import br.edu.utfpr.email.EnvioEmail;
import br.edu.utfpr.whatsapp.EnvioWhatsapp;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProcessaAtividadeMedicamento extends ProcessaAtividade<AtividadeMedicamentoResidenteModel, AtividadeMedicamentoResidenteRepository> {

    public ProcessaAtividadeMedicamento(AtividadeMedicamentoResidenteRepository repository, EnvioEmail envioEmail, EnvioWhatsapp envioWhatsapp) {
        super(repository, envioEmail, envioWhatsapp);
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

}
