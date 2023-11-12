package br.edu.utfpr.dashboard;

import br.edu.utfpr.atividadeludica.AtividadeLudicaRepository;
import br.edu.utfpr.atividadesresidente.atividadeconsultaresidente.AtividadeConsultaResidenteRepository;
import br.edu.utfpr.atividadesresidente.atividadeexameresidente.AtividadeExameResidenteRepository;
import br.edu.utfpr.atividadesresidente.atividademedicamentoresidente.AtividadeMedicamentoResidenteRepository;
import br.edu.utfpr.consulta.ConsultaRepository;
import br.edu.utfpr.entradasaida.EntradaSaidaRepository;
import br.edu.utfpr.exame.ExameRepository;
import br.edu.utfpr.medicamentoestoque.MedicamentoEstoqueRepository;
import br.edu.utfpr.profissional.ProfissionalRepository;
import br.edu.utfpr.residente.ResidenteRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DashboardService extends MontaGrafico {

    private ProfissionalRepository profissionalRepository;
    private ResidenteRepository residenteRepository;
    private EntradaSaidaRepository entradaSaidaRepository;
    private MedicamentoEstoqueRepository medicamentoEstoqueRepository;
    private ConsultaRepository consultaRepository;
    private ExameRepository exameRepository;
    private AtividadeLudicaRepository atividadeLudicaRepository;
    private AtividadeConsultaResidenteRepository atividadeConsultaResidenteRepository;
    private AtividadeExameResidenteRepository atividadeExameResidenteRepository;
    private AtividadeMedicamentoResidenteRepository atividadeMedicamentoResidenteRepository;

    @Inject
    public DashboardService(
            ProfissionalRepository profissionalRepository,
            ResidenteRepository residenteRepository,
            EntradaSaidaRepository entradaSaidaRepository,
            MedicamentoEstoqueRepository medicamentoEstoqueRepository,
            ConsultaRepository consultaRepository,
            ExameRepository exameRepository,
            AtividadeLudicaRepository atividadeLudicaRepository,
            AtividadeConsultaResidenteRepository atividadeConsultaResidenteRepository,
            AtividadeExameResidenteRepository atividadeExameResidenteRepository,
            AtividadeMedicamentoResidenteRepository atividadeMedicamentoResidenteRepository
        ) {
        this.profissionalRepository = profissionalRepository;
        this.residenteRepository = residenteRepository;
        this.entradaSaidaRepository = entradaSaidaRepository;
        this.medicamentoEstoqueRepository = medicamentoEstoqueRepository;
        this.consultaRepository = consultaRepository;
        this.exameRepository = exameRepository;
        this.atividadeLudicaRepository = atividadeLudicaRepository;
        this.atividadeConsultaResidenteRepository = atividadeConsultaResidenteRepository;
        this.atividadeExameResidenteRepository = atividadeExameResidenteRepository;
        this.atividadeMedicamentoResidenteRepository = atividadeMedicamentoResidenteRepository;
    }

    public Response getAll(){

        List<DashboardDTO> lista = new ArrayList<>();

        List<DadoDTO> dadosProfissional = profissionalRepository.getDadosFuncaoDash();
        lista.add(montaGrafico(dadosProfissional, "Profissionais"));

        List<DadoDTO> dadosEstadiaResidente = residenteRepository.getDadosEstadiaDash();
        lista.add(montaGrafico(dadosEstadiaResidente, "Residentes por tipo de estadia"));

        List<DadoDTO> dadosIngresso30DiasResidente = residenteRepository.getDadosIngresso30diasDash(LocalDateTime.now().minusDays(30), LocalDateTime.now());
        lista.add(montaGrafico(dadosIngresso30DiasResidente, "Residentes novos últimos 30 dias"));


        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lista).build();
    }

}
