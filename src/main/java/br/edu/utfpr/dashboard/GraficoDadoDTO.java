package br.edu.utfpr.dashboard;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GraficoDadoDTO {

    private List<Long> data;
    private List<String> backgroundColor;

}
