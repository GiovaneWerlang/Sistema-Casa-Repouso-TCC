package br.edu.utfpr.medicamentoestoque;

import br.edu.utfpr.crud.CrudRepository;
import br.edu.utfpr.dashboard.DadoDTO;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class MedicamentoEstoqueRepository extends CrudRepository<MedicamentoEstoqueModel> {

    public List<DadoDTO> getDadosMenorQtdeDash(){
        return find("select me.principioAtivo as label, min(me.qtde) as data from MedicamentoEstoqueModel me group by me.principioAtivo").project(DadoDTO.class).list();
    }
}
