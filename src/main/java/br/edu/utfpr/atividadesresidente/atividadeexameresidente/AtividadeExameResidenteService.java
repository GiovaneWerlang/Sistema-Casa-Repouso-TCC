package br.edu.utfpr.atividadesresidente.atividadeexameresidente;

import br.edu.utfpr.atividadesresidente.AtividadeResidenteDTO;
import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.profissional.ProfissionalModel;
import br.edu.utfpr.profissional.ProfissionalRepository;
import br.edu.utfpr.utils.PageDTO;
import br.edu.utfpr.utils.ResponseUtils;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class AtividadeExameResidenteService {

    private AtividadeExameResidenteRepository repository;
    private Validator validator;
    private ProfissionalRepository profissionalRepository;

    @Inject
    public  AtividadeExameResidenteService(AtividadeExameResidenteRepository repository, Validator validator, ProfissionalRepository profissionalRepository) {
        this.repository = repository;
        this.validator = validator;
        this.profissionalRepository = profissionalRepository;
    }

    public Response getAll(){
        List< AtividadeExameResidenteModel> lista = repository.findByTime();
        if(lista.isEmpty()){
            return ResponseUtils.notFound();
        }
        return ResponseUtils.okListaModel(lista);
    }

    public Response findById(long id){
        AtividadeExameResidenteModel model = repository.findById(id);
        if(model != null){
            return ResponseUtils.okModel(model);
        }

        return ResponseUtils.notFound();
    }

    public Response update(long id, AtividadeResidenteDTO atividadeResidenteDTO){
        Set<ConstraintViolation<AtividadeResidenteDTO>> violations = validator.validate(atividadeResidenteDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        AtividadeExameResidenteModel model = repository.findById(id);
        ProfissionalModel profissionalModel = profissionalRepository.findById(atividadeResidenteDTO.getProfissional());
        if(model != null && profissionalModel != null){
            model.setSituacao(atividadeResidenteDTO.getSituacao());
            model.setProfissional(profissionalModel);

            return ResponseUtils.atualizadoPorCodigo(model.getId());
        }

        return ResponseUtils.notFound();
    }

    public Response pageSort(int page, int size, String atributo, boolean asc){
        if(page < 0 || size < 1){
            return ResponseUtils.porCodigo(422);
        }
        List<AtividadeExameResidenteModel> lista = repository.pageListSort(page,size,atributo,asc ? Sort.Direction.Ascending : Sort.Direction.Descending);
        if(lista.isEmpty()){
            return ResponseUtils.notFound();
        }
        PageDTO<AtividadeExameResidenteModel> pageDTO = new PageDTO<>();
        pageDTO.setLista(lista);
        pageDTO.setPages(repository.pageCount(page,size));
        pageDTO.setTotal(repository.pageTotal(page,size));
        return ResponseUtils.okPage(pageDTO);
    }

}
