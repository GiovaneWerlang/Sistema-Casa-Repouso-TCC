package br.edu.utfpr.entradasaida;

import br.edu.utfpr.crud.CrudRepository;
import br.edu.utfpr.dashboard.DadoDTO;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class EntradaSaidaRepository extends CrudRepository<EntradaSaidaModel> {
    public List<DadoDTO> getDadosEntradaSaidaResidentesDash(){
        return find("select r.nome as label, count(e.id) as data from EntradaSaidaModel e join ResidenteModel r on r.id = e.residente.id group by r.nome").project(DadoDTO.class).list();
    }
}
