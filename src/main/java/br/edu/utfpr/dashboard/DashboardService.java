package br.edu.utfpr.dashboard;

import br.edu.utfpr.profissional.ProfissionalRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class DashboardService {

    private ProfissionalRepository profissionalRepository;

    @Inject
    public DashboardService(ProfissionalRepository profissionalRepository) {
        this.profissionalRepository = profissionalRepository;
    }

    public Response getAll(){
        List<DashboardDTO> lista = new ArrayList<>();
        lista.add(
                new DashboardDTO(
                        "Teste1",
                        Arrays.asList("Item 1","Item 2","Item 3"),
                        Arrays.asList(
                                new GraficoDadoDTO(
                                        Arrays.asList(1,2,3),
                                        Arrays.asList("#343434","#F8F8F8","#747474")
                                )
                        )
                )
        );
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lista).build();
    }

}
