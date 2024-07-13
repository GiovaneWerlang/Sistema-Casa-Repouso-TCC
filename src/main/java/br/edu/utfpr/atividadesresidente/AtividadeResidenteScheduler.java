package br.edu.utfpr.atividadesresidente;

import br.edu.utfpr.atividadesresidente.atividadeconsultaresidente.AtividadeConsultaResidenteRepository;
import br.edu.utfpr.atividadesresidente.atividadeexameresidente.AtividadeExameResidenteRepository;
import br.edu.utfpr.atividadesresidente.atividademedicamentoresidente.AtividadeMedicamentoResidenteRepository;
import br.edu.utfpr.atividadesresidente.processa.*;
import br.edu.utfpr.configuracaosistema.ConfiguracaoSistemaModel;
import br.edu.utfpr.configuracaosistema.ConfiguracaoSistemaRepository;
import br.edu.utfpr.crud.CrudRepositoryAtividade;
import br.edu.utfpr.email.EnvioEmail;
import br.edu.utfpr.profissional.ProfissionalService;
import br.edu.utfpr.whatsapp.EnvioWhatsapp;
import io.quarkus.scheduler.Scheduled;
import io.quarkus.scheduler.ScheduledExecution;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class AtividadeResidenteScheduler {

    private AtividadeConsultaResidenteRepository atividadeConsultaResidenteRepository;

    private AtividadeExameResidenteRepository atividadeExameResidenteRepository;
    private AtividadeMedicamentoResidenteRepository atividadeMedicamentoResidenteRepository;
    private ConfiguracaoSistemaRepository configuracaoSistemaRepository;

    private ProfissionalService profissionalService;

    private EnvioEmail envioEmail;
    private EnvioWhatsapp envioWhatsapp;

    @Inject
    public AtividadeResidenteScheduler(AtividadeConsultaResidenteRepository atividadeConsultaResidenteRepository,
                                       AtividadeExameResidenteRepository atividadeExameResidenteRepository,
                                       AtividadeMedicamentoResidenteRepository atividadeMedicamentoResidenteRepository,
                                       ConfiguracaoSistemaRepository configuracaoSistemaRepository,
                                       ProfissionalService profissionalService,
                                       EnvioEmail envioEmail,
                                       EnvioWhatsapp envioWhatsapp
    ) {
        this.atividadeConsultaResidenteRepository = atividadeConsultaResidenteRepository;
        this.atividadeExameResidenteRepository = atividadeExameResidenteRepository;
        this.atividadeMedicamentoResidenteRepository = atividadeMedicamentoResidenteRepository;
        this.configuracaoSistemaRepository = configuracaoSistemaRepository;
        this.profissionalService = profissionalService;
        this.envioEmail = envioEmail;
        this.envioWhatsapp = envioWhatsapp;
    }

    @Scheduled(every="1h")
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
        ProcessaAtividade<? extends AtividadeResidenteModel, ? extends CrudRepositoryAtividade<?>> processaAtividade;
        ConfiguracaoSistemaModel configuracaoSistema = configuracaoSistemaRepository.findById(1L);

        processaAtividade = new ProcessaAtividadeConsulta(atividadeConsultaResidenteRepository,configuracaoSistema, envioEmail, envioWhatsapp);
        processaAtividade.processarLista(emails, telefones);

        processaAtividade = new ProcessaAtividadeExame(atividadeExameResidenteRepository,configuracaoSistema, envioEmail, envioWhatsapp);
        processaAtividade.processarLista(emails, telefones);

        processaAtividade = new ProcessaAtividadeMedicamento(atividadeMedicamentoResidenteRepository,configuracaoSistema, envioEmail, envioWhatsapp);
        processaAtividade.processarLista(emails, telefones);
    }

}
