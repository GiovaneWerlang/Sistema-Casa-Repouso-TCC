package br.edu.utfpr.atividadesresidente.atividadeconsultaresidente;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class AtividadeConsultaResidenteService {

    private AtividadeConsultaResidenteRepository repository;

    @Inject
    public AtividadeConsultaResidenteService(AtividadeConsultaResidenteRepository repository) {
        this.repository = repository;
    }

    public Response getAll(){
        List<AtividadeConsultaResidenteModel> lista = repository.findByTime();
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lista).build();
    }

}
