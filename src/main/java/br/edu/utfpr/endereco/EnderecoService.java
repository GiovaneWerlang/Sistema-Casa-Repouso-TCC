package br.edu.utfpr.endereco;

import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.utils.Copy;
import br.edu.utfpr.utils.ResponseUtils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class EnderecoService {

    private EnderecoRepository repository;
    private Validator validator;

    @Inject
    public EnderecoService(EnderecoRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Response getAll(){
        List<EnderecoModel> lista = repository.listAll();
        if(lista.isEmpty()){
            return ResponseUtils.notFound();
        }
        return ResponseUtils.okListaModel(lista);
    }

    public Response findById(long id){
        EnderecoModel model = repository.findById(id);
        if(model != null){
            return ResponseUtils.okModel(model);
        }

        return ResponseUtils.notFound();
    }

    public Response add(EnderecoDTO enderecoDTO){
        Set<ConstraintViolation<EnderecoDTO>> violations = validator.validate(enderecoDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        EnderecoModel model = new EnderecoModel();

        if(!Copy.copyProperties(model, enderecoDTO)){
            return ResponseUtils.porCodigo(418);
        }

        try{
            repository.persist(model);
        }catch (Exception ex){
            return ResponseUtils.serverError();
        }

        return ResponseUtils.criado(model.getId());
    }

    public Response update(long id, EnderecoDTO enderecoDTO){
        Set<ConstraintViolation<EnderecoDTO>> violations = validator.validate(enderecoDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        EnderecoModel model = repository.findById(id);
        if(model != null){
            if(!Copy.copyProperties(model, enderecoDTO)){
                return ResponseUtils.porCodigo(418);
            }

            return ResponseUtils.atualizadoPorCodigo(model.getId());
        }

        return ResponseUtils.notFound();
    }

    public Response delete(long id){
        EnderecoModel model = repository.findById(id);
        if(model != null){
            repository.delete(model);
            return ResponseUtils.okModel(model);
        }

        return ResponseUtils.notFound();
    }
}
