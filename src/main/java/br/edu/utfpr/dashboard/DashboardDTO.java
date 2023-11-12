package br.edu.utfpr.dashboard;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDTO {

    private String titulo;
    private List<String> labels;
    private List<GraficoDadoDTO> datasets;
}
