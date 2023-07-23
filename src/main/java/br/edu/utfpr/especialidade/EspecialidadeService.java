package br.edu.utfpr.especialidade;

import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.crud.CrudService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class EspecialidadeService implements CrudService<EspecialidadeDTO> {

    private EspecialidadeRepository repository;
    private Validator validator;

    @Inject
    public EspecialidadeService(EspecialidadeRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Response getAll(){
        List<EspecialidadeModel> lista = repository.listAll();
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lista).build();
    }

    public Response findById(long id){
        EspecialidadeModel model = repository.findById(id);
        if(model != null){
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
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
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status( Response.Status.CREATED.getStatusCode(),model.toString()).build();
    }

    public Response update(long id, EspecialidadeDTO especialidadeDTO){
        Set<ConstraintViolation<EspecialidadeDTO>> violations = validator.validate(especialidadeDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        EspecialidadeModel model = repository.findById(id);
        if(model != null){
            model.setNome(especialidadeDTO.getNome());

            return Response.status(201, model.toString()).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response delete(long id){
        EspecialidadeModel model = repository.findById(id);
        if(model != null){
            repository.delete(model);
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
