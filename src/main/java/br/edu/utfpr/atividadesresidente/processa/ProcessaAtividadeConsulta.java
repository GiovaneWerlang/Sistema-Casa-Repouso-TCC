package br.edu.utfpr.atividadesresidente.processa;

import br.edu.utfpr.atividadesresidente.atividadeconsultaresidente.AtividadeConsultaResidenteModel;
import br.edu.utfpr.atividadesresidente.atividadeconsultaresidente.AtividadeConsultaResidenteRepository;
import br.edu.utfpr.configuracaosistema.ConfiguracaoSistemaModel;
import br.edu.utfpr.email.EnvioEmail;
import br.edu.utfpr.whatsapp.EnvioWhatsapp;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProcessaAtividadeConsulta extends ProcessaAtividade<AtividadeConsultaResidenteModel, AtividadeConsultaResidenteRepository> {

    public ProcessaAtividadeConsulta(AtividadeConsultaResidenteRepository repository, ConfiguracaoSistemaModel configuracaoSistema, EnvioEmail envioEmail, EnvioWhatsapp envioWhatsapp) {
        super(repository, configuracaoSistema, envioEmail, envioWhatsapp);
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

}
