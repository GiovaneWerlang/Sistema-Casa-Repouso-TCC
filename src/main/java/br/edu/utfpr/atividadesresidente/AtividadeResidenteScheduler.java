package br.edu.utfpr.atividadesresidente;

import br.edu.utfpr.atividadesresidente.atividadeconsultaresidente.AtividadeConsultaResidenteRepository;
import br.edu.utfpr.atividadesresidente.atividadeexameresidente.AtividadeExameResidenteRepository;
import br.edu.utfpr.atividadesresidente.atividademedicamentoresidente.AtividadeMedicamentoResidenteRepository;
import br.edu.utfpr.atividadesresidente.processa.ProcessaAtividade;
import br.edu.utfpr.atividadesresidente.processa.ProcessaAtividadeConsulta;
import br.edu.utfpr.atividadesresidente.processa.ProcessaAtividadeExame;
import br.edu.utfpr.atividadesresidente.processa.ProcessaAtividadeMedicamento;
import br.edu.utfpr.email.EnvioEmail;
import br.edu.utfpr.profissional.ProfissionalService;
import br.edu.utfpr.whatsapp.EnvioWhatsapp;
import io.quarkus.scheduler.Scheduled;
import io.quarkus.scheduler.ScheduledExecution;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class AtividadeResidenteScheduler {

    private AtividadeConsultaResidenteRepository atividadeConsultaResidenteRepository;

    private AtividadeExameResidenteRepository atividadeExameResidenteRepository;
    private AtividadeMedicamentoResidenteRepository atividadeMedicamentoResidenteRepository;

    private ProfissionalService profissionalService;

    private EnvioEmail envioEmail;
    private EnvioWhatsapp envioWhatsapp;

    @Inject
    public AtividadeResidenteScheduler(AtividadeConsultaResidenteRepository atividadeConsultaResidenteRepository,
                                       AtividadeExameResidenteRepository atividadeExameResidenteRepository,
                                       AtividadeMedicamentoResidenteRepository atividadeMedicamentoResidenteRepository,
                                       ProfissionalService profissionalService,
                                       EnvioEmail envioEmail,
                                       EnvioWhatsapp envioWhatsapp
    ) {
        this.atividadeConsultaResidenteRepository = atividadeConsultaResidenteRepository;
        this.atividadeExameResidenteRepository = atividadeExameResidenteRepository;
        this.atividadeMedicamentoResidenteRepository = atividadeMedicamentoResidenteRepository;
        this.profissionalService = profissionalService;
        this.envioEmail = envioEmail;
        this.envioWhatsapp = envioWhatsapp;
    }

    //@Scheduled(every="1h")
    @Scheduled(every="2m")
    void cronJob(ScheduledExecution execution) {
        processarAtividades(buscarEmails(), buscarTelefones());
    }

    public List<String> buscarEmails(){
        return profissionalService.buscarEmails();
    }

    public List<String> buscarTelefones(){
        return profissionalService.buscarTelefones();
    }

    @Transactional
    public void processarAtividades(List<String> emails, List<String> telefones){
        ProcessaAtividade processaAtividade;

        processaAtividade = new ProcessaAtividadeConsulta(atividadeConsultaResidenteRepository, envioEmail, envioWhatsapp);
        processaAtividade.processarLista(emails, telefones);

        processaAtividade = new ProcessaAtividadeExame(atividadeExameResidenteRepository, envioEmail, envioWhatsapp);
        processaAtividade.processarLista(emails, telefones);

        processaAtividade = new ProcessaAtividadeMedicamento(atividadeMedicamentoResidenteRepository, envioEmail, envioWhatsapp);
        processaAtividade.processarLista(emails, telefones);
    }

}
