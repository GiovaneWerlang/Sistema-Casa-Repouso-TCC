package br.edu.utfpr.profissional;

import br.edu.utfpr.crud.CrudRepository;
import br.edu.utfpr.dashboard.DadoDTO;
import br.edu.utfpr.enums.Funcao;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ProfissionalRepository extends CrudRepository<ProfissionalModel> {
    public List<DadoDTO> getDadosFuncaoDash(){
        return find("select p.funcao as label, COUNT(p.id) as data from ProfissionalModel p group by p.funcao").project(DadoDTO.class).list();
    }

    public List<String> findEmailByFuncaoCuidador(){
        return find("select p.email from ProfissionalModel p where p.funcao = ?1", Funcao.CUIDADOR).project(String.class).list();
    }

    public List<String> findTelefoneByFuncaoCuidador(){
        return find("select p.telefone from ProfissionalModel p where p.funcao = ?1", Funcao.CUIDADOR).project(String.class).list();
    }

}
