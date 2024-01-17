package br.edu.utfpr.atividadesresidente.processa;

import br.edu.utfpr.atividadesresidente.atividadeexameresidente.AtividadeExameResidenteModel;
import br.edu.utfpr.atividadesresidente.atividadeexameresidente.AtividadeExameResidenteRepository;
import br.edu.utfpr.email.EnvioEmail;
import br.edu.utfpr.whatsapp.EnvioWhatsapp;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProcessaAtividadeExame extends ProcessaAtividade<AtividadeExameResidenteModel, AtividadeExameResidenteRepository> {

    public ProcessaAtividadeExame(AtividadeExameResidenteRepository repository, EnvioEmail envioEmail, EnvioWhatsapp envioWhatsapp) {
        super(repository, envioEmail, envioWhatsapp);
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

}
