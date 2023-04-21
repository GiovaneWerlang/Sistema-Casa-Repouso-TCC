package br.edu.utfpr.atividadeludica;

import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.utils.Copy;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class AtividadeLudicaService {

    private AtividadeLudicaRepository repository;
    private Validator validator;

    @Inject
    public AtividadeLudicaService(AtividadeLudicaRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Response getAll(){
        List<AtividadeLudicaModel> lista = repository.listAll();
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lista).build();
    }

    public Response findById(long id){
        AtividadeLudicaModel model = repository.findById(id);
        if(model != null){
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response add(AtividadeLudicaDTO atividadeLudicaDTO){
        Set<ConstraintViolation<AtividadeLudicaDTO>> violations = validator.validate(atividadeLudicaDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        AtividadeLudicaModel model = new AtividadeLudicaModel();

        if(!Copy.copyProperties(model, atividadeLudicaDTO)){
            return Response.status(418).build();
        }

        try{
            repository.persist(model);
        }catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status( Response.Status.CREATED.getStatusCode(),model.toString()).build();
    }

    public Response update(long id, AtividadeLudicaDTO atividadeLudicaDTO){
        Set<ConstraintViolation<AtividadeLudicaDTO>> violations = validator.validate(atividadeLudicaDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        AtividadeLudicaModel model = repository.findById(id);
        if(model != null){

            if(!Copy.copyProperties(model, atividadeLudicaDTO)){
                return Response.status(418).build();
            }

            return Response.status(201, model.toString()).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response delete(long id){
        AtividadeLudicaModel model = repository.findById(id);
        if(model != null){
            repository.delete(model);
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
