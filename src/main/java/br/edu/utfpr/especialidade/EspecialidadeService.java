package br.edu.utfpr.especialidade;

import br.edu.utfpr.crud.CrudService;
import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.utils.ResponseUtils;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class EspecialidadeService extends CrudService<EspecialidadeModel, EspecialidadeDTO, EspecialidadeRepository> {

    private EspecialidadeRepository repository;
    private Validator validator;

    public EspecialidadeService(EspecialidadeRepository repository, Validator validator) {
        super(repository, validator);
        this.repository = repository;
        this.validator = validator;
    }

    public Response getAll(){
        List<EspecialidadeModel> lista = repository.listAll(Sort.by("id"));
        if(lista.isEmpty()){
            return ResponseUtils.notFound();
        }
        return ResponseUtils.okListaModel(lista);
    }

    public Response findById(long id){
        EspecialidadeModel model = repository.findById(id);
        if(model != null){
            return ResponseUtils.okModel(model);
        }

        return ResponseUtils.notFound();
    }

    public Response add(EspecialidadeDTO especialidadeDTO){
        Set<ConstraintViolation<EspecialidadeDTO>> violations = validator.validate(especialidadeDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        EspecialidadeModel model = new EspecialidadeModel();
        model.setNome(especialidadeDTO.getNome());

        try{
            repository.persist(model);
        }catch (Exception ex){
            return ResponseUtils.serverError();
        }

        return ResponseUtils.criado(model.getId());
    }

    public Response update(long id, EspecialidadeDTO especialidadeDTO){
        Set<ConstraintViolation<EspecialidadeDTO>> violations = validator.validate(especialidadeDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        EspecialidadeModel model = repository.findById(id);
        if(model != null){
            model.setNome(especialidadeDTO.getNome());

            return ResponseUtils.atualizadoPorCodigo(model.getId());
        }

        return ResponseUtils.notFound();
    }

    public Response delete(long id){
        EspecialidadeModel model = repository.findById(id);
        if(model != null){
            repository.delete(model);
            return ResponseUtils.okModel(model);
        }

        return ResponseUtils.notFound();
    }

}
