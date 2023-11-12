package br.edu.utfpr.dashboard;

import br.edu.utfpr.dashboard.enums.Cores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public abstract class MontaGrafico {

    private final List<String> cores = new ArrayList<>(Stream.of(Cores.values()).map(Cores::getCor).toList());

    public DashboardDTO montaGrafico(List<DadoDTO> dadosDTO, String titulo) {
        if (dadosDTO != null && !dadosDTO.isEmpty()) {
            return DashboardDTO.builder()
                    .titulo(titulo)
                    .labels(dadosDTO.stream().map(l -> l.getLabel()).toList())
                    .datasets(
                            Collections.singletonList(GraficoDadoDTO.builder()
                                    .data(dadosDTO.stream().map(dt -> dt.getData()).toList())
                                    .backgroundColor(cores)
                                    .build()
                            )
                    )
                    .build();
        }else {
            return DashboardDTO.builder()
                    .titulo(titulo)
                    .build();
        }
    }
}
