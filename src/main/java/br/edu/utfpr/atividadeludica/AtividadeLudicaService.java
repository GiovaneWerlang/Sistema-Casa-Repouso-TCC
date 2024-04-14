package br.edu.utfpr.atividadeludica;

import br.edu.utfpr.crud.CrudService;
import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.utils.Copy;
import br.edu.utfpr.utils.PageDTO;
import br.edu.utfpr.utils.ResponseUtils;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@ApplicationScoped
public class AtividadeLudicaService extends CrudService<AtividadeLudicaModel, AtividadeLudicaDTO, AtividadeLudicaRepository> {

    private AtividadeLudicaRepository repository;
    private Validator validator;

    @Inject
    public AtividadeLudicaService(AtividadeLudicaRepository repository, Validator validator) {
        super(repository, validator);
        this.repository = repository;
        this.validator = validator;
    }

    public Response getAll(){
        List<AtividadeLudicaModel> lista = repository.listAll();
        if(lista.isEmpty()){
            return ResponseUtils.notFound();
        }
        return ResponseUtils.okListaModel(lista);
    }

    public Response findById(long id){
        AtividadeLudicaModel model = repository.findById(id);
        if(model != null){
            return ResponseUtils.okModel(model);
        }

        return ResponseUtils.notFound();
    }

    public Response add(AtividadeLudicaDTO atividadeLudicaDTO){
        Set<ConstraintViolation<AtividadeLudicaDTO>> violations = validator.validate(atividadeLudicaDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        AtividadeLudicaModel model = new AtividadeLudicaModel();

        if(!Copy.copyProperties(model, atividadeLudicaDTO)){
            return ResponseUtils.porCodigo(418);
        }

        try{
            repository.persist(model);
        }catch (Exception ex){
            return ResponseUtils.serverError();
        }
        return ResponseUtils.criado(model.getId());
    }

    public Response update(long id, AtividadeLudicaDTO atividadeLudicaDTO){
        Set<ConstraintViolation<AtividadeLudicaDTO>> violations = validator.validate(atividadeLudicaDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        AtividadeLudicaModel model = repository.findById(id);
        if(model != null){

            if(!Copy.copyProperties(model, atividadeLudicaDTO)){
                return ResponseUtils.porCodigo(418);
            }

            return ResponseUtils.atualizadoPorCodigo(model.getId());
        }

        return ResponseUtils.notFound();
    }

    public Response delete(long id){
        AtividadeLudicaModel model = repository.findById(id);
        if(model != null){
            repository.delete(model);
            return ResponseUtils.okModel(model);
        }

        return ResponseUtils.notFound();
    }

}
