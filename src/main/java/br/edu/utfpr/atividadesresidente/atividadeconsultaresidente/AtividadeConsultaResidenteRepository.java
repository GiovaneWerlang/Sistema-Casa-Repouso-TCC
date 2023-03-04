package br.edu.utfpr.atividadesresidente.atividadeconsultaresidente;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AtividadeConsultaResidenteRepository implements PanacheRepository<AtividadeConsultaResidenteModel> {
    public AtividadeConsultaResidenteModel findByConsultaId(Long id){
        return find("idconsulta", id).firstResult();
    }
}
