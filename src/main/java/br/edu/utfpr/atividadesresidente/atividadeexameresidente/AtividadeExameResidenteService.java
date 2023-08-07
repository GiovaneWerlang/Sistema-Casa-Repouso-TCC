package br.edu.utfpr.atividadesresidente.atividadeexameresidente;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class AtividadeExameResidenteService {

    private AtividadeExameResidenteRepository repository;

    @Inject
    public  AtividadeExameResidenteService(AtividadeExameResidenteRepository repository) {
        this.repository = repository;
    }

    public Response getAll(){
        List< AtividadeExameResidenteModel> lista = repository.findByTime();
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lista).build();
    }

}
