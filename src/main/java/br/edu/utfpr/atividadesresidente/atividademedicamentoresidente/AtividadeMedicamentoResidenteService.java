package br.edu.utfpr.atividadesresidente.atividademedicamentoresidente;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class AtividadeMedicamentoResidenteService {

    private AtividadeMedicamentoResidenteRepository repository;

    @Inject
    public AtividadeMedicamentoResidenteService(AtividadeMedicamentoResidenteRepository repository) {
        this.repository = repository;
    }

    public Response getAll(){
        List<AtividadeMedicamentoResidenteModel> lista = repository.findByTime();
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lista).build();
    }

}
