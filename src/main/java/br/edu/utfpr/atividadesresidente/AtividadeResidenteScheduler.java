package br.edu.utfpr.atividadesresidente;

import br.edu.utfpr.atividadesresidente.atividadeconsultaresidente.AtividadeConsultaResidenteRepository;
import br.edu.utfpr.atividadesresidente.atividadeexameresidente.AtividadeExameResidenteRepository;
import br.edu.utfpr.atividadesresidente.atividademedicamentoresidente.AtividadeMedicamentoResidenteRepository;
import br.edu.utfpr.atividadesresidente.processa.ProcessaAtividade;
import br.edu.utfpr.atividadesresidente.processa.ProcessaAtividadeConsulta;
import br.edu.utfpr.atividadesresidente.processa.ProcessaAtividadeExame;
import br.edu.utfpr.atividadesresidente.processa.ProcessaAtividadeMedicamento;
import br.edu.utfpr.profissional.ProfissionalService;
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

    @Inject
    public AtividadeResidenteScheduler(AtividadeConsultaResidenteRepository atividadeConsultaResidenteRepository,
                                       AtividadeExameResidenteRepository atividadeExameResidenteRepository,
                                       AtividadeMedicamentoResidenteRepository atividadeMedicamentoResidenteRepository,
                                       ProfissionalService profissionalService) {
        this.atividadeConsultaResidenteRepository = atividadeConsultaResidenteRepository;
        this.atividadeExameResidenteRepository = atividadeExameResidenteRepository;
        this.atividadeMedicamentoResidenteRepository = atividadeMedicamentoResidenteRepository;
        this.profissionalService = profissionalService;
    }

    @Scheduled(every="1h")
    void cronJob(ScheduledExecution execution) {
        processarAtividades(buscarEmails());
    }

    public List<String> buscarEmails(){
        return profissionalService.buscarEmails();
    }

    @Transactional
    public void processarAtividades( List<String> emails){
        ProcessaAtividade processaAtividade;

        processaAtividade = new ProcessaAtividadeConsulta(atividadeConsultaResidenteRepository);
        processaAtividade.processarLista(emails);

        processaAtividade = new ProcessaAtividadeExame(atividadeExameResidenteRepository);
        processaAtividade.processarLista(emails);

        processaAtividade = new ProcessaAtividadeMedicamento(atividadeMedicamentoResidenteRepository);
        processaAtividade.processarLista(emails);
    }

}
