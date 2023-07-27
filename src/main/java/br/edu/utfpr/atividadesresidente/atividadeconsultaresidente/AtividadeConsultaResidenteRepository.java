package br.edu.utfpr.atividadesresidente.atividadeconsultaresidente;

import br.edu.utfpr.crud.CrudRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AtividadeConsultaResidenteRepository extends CrudRepository<AtividadeConsultaResidenteModel> {
    public AtividadeConsultaResidenteModel findByConsultaId(Long id){
        return find("idconsulta", id).firstResult();
    }
}
