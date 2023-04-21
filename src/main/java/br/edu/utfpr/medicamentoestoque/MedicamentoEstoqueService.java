package br.edu.utfpr.medicamentoestoque;

import br.edu.utfpr.erro.ResponseError;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;


@ApplicationScoped
public class MedicamentoEstoqueService {

    private MedicamentoEstoqueRepository repository;
    private Validator validator;

    @Inject
    public MedicamentoEstoqueService(MedicamentoEstoqueRepository repository, Validator validator){
        this.repository = repository;
        this.validator = validator;
    }

    public Response getAll(){
        List<MedicamentoEstoqueModel> lista = repository.listAll();
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lista).build();
    }

    public Response findById(long id){
        MedicamentoEstoqueModel model = repository.findById(id);
        if(model != null){
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response add(MedicamentoEstoqueDTO medicamentoEstoqueDTO){
        Set<ConstraintViolation<MedicamentoEstoqueDTO>> violations = validator.validate(medicamentoEstoqueDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        MedicamentoEstoqueModel model = new MedicamentoEstoqueModel();
        model.setNome(medicamentoEstoqueDTO.getNome());
        model.setPrincipioAtivo(medicamentoEstoqueDTO.getPrincipioAtivo());
        model.setQtde(medicamentoEstoqueDTO.getQtde());

        try{
            repository.persist(model);
        }catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status( Response.Status.CREATED.getStatusCode(),model.toString()).build();
    }

    public Response update(long id, MedicamentoEstoqueDTO medicamentoEstoqueDTO){
        Set<ConstraintViolation<MedicamentoEstoqueDTO>> violations = validator.validate(medicamentoEstoqueDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        MedicamentoEstoqueModel model = repository.findById(id);
        if(model != null){
            model.setNome(medicamentoEstoqueDTO.getNome());
            model.setPrincipioAtivo(medicamentoEstoqueDTO.getPrincipioAtivo());
            model.setQtde(medicamentoEstoqueDTO.getQtde());

            return Response.status(201, model.toString()).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response delete(long id){
        MedicamentoEstoqueModel model = repository.findById(id);
        if(model != null){
            repository.delete(model);
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
