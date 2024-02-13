package br.edu.utfpr.atividadesresidente.notificacao;

import br.edu.utfpr.atividadesresidente.atividadeconsultaresidente.AtividadeConsultaResidenteRepository;
import br.edu.utfpr.atividadesresidente.atividadeexameresidente.AtividadeExameResidenteRepository;
import br.edu.utfpr.atividadesresidente.atividademedicamentoresidente.AtividadeMedicamentoResidenteRepository;
import br.edu.utfpr.dashboard.AtividadeDashDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class NotificacaoService {


    private AtividadeConsultaResidenteRepository atividadeConsultaResidenteRepository;
    private AtividadeExameResidenteRepository atividadeExameResidenteRepository;
    private AtividadeMedicamentoResidenteRepository atividadeMedicamentoResidenteRepository;

    @Inject
    public NotificacaoService(AtividadeConsultaResidenteRepository atividadeConsultaResidenteRepository, AtividadeExameResidenteRepository atividadeExameResidenteRepository, AtividadeMedicamentoResidenteRepository atividadeMedicamentoResidenteRepository) {
        this.atividadeConsultaResidenteRepository = atividadeConsultaResidenteRepository;
        this.atividadeExameResidenteRepository = atividadeExameResidenteRepository;
        this.atividadeMedicamentoResidenteRepository = atividadeMedicamentoResidenteRepository;
    }

    public List<MensagemNotificacao> getMensagens(){
        List<MensagemNotificacao> mensagens = new ArrayList<>();

        List<AtividadeDashDTO> listaAtividades = buscarAtividades();

        for(AtividadeDashDTO atividadeDashDTO : listaAtividades){
            mensagens.add(
                MensagemNotificacao.builder().notification(
                    Notificacao.builder()
                        .title("Lembre de atividade de residente. " + montaMensagem(atividadeDashDTO))
                        .actions(
                            Arrays.asList(
                                AcoesNotificacao.builder()
                                        .action("open")
                                        .title("Abrir")
                                        .build()
                            )
                        )
                        .data(
                            DadosNotificacao.builder()
                                .onActionClick(
                                    AcaoClickNotificacao.builder()
                                        .open(
                                            AcaoDefault.builder()
                                                    .operation("navigateLastFocusedOrOpen")
                                                    .url(montaUrl(atividadeDashDTO))
                                                    .build()
                                        )
                                        .build()
                                )
                                .build()
                        )
                        .build()
                ).build()
            );
        }

        return mensagens;
    }

    private List<AtividadeDashDTO> buscarAtividades(){
        List<AtividadeDashDTO> listaAtividades = new ArrayList<>();
        listaAtividades.addAll(atividadeConsultaResidenteRepository.findByTimeDTO());
        listaAtividades.addAll(atividadeExameResidenteRepository.findByTimeDTO());
        listaAtividades.addAll(atividadeMedicamentoResidenteRepository.findByTimeDTO());
        return listaAtividades;
    }

    private String montaMensagem(AtividadeDashDTO atividadeDashDTO){
        StringBuilder sb = new StringBuilder();
        sb.append(atividadeDashDTO.getDescricao());
        sb.append(" agendada para o dia ");
        sb.append(atividadeDashDTO.getDataHora().getDayOfMonth());
        sb.append(", às ");
        sb.append(atividadeDashDTO.getDataHora().getHour());
        sb.append(":");
        sb.append(atividadeDashDTO.getDataHora().getMinute());
        sb.append(" horas");
        return sb.toString();
    }

    private String montaUrl(AtividadeDashDTO atividadeDashDTO){
        StringBuilder sb = new StringBuilder();
        sb.append("#/atividade");
        sb.append(atividadeDashDTO.getTipo().toLowerCase());
        sb.append("/editar/");
        sb.append(atividadeDashDTO.getId());

        return sb.toString();
    }
}
