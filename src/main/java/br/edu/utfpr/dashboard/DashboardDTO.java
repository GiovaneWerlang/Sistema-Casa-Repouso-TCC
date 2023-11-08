package br.edu.utfpr.dashboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDTO {

    private String titulo;
    private List<String> labels;
    private List<GraficoDadoDTO> datasets;
}
