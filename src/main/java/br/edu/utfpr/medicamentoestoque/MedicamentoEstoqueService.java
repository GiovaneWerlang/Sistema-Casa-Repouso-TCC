package br.edu.utfpr.medicamentoestoque;

import br.edu.utfpr.crud.CrudService;
import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.utils.Copy;
import br.edu.utfpr.utils.PageDTO;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;


@ApplicationScoped
public class MedicamentoEstoqueService implements CrudService<MedicamentoEstoqueDTO> {

    private MedicamentoEstoqueRepository repository;
    private Validator validator;

    @Inject
    public MedicamentoEstoqueService(MedicamentoEstoqueRepository repository, Validator validator){
        this.repository = repository;
        this.validator = validator;
    }

    public Response getAll(){
        List<MedicamentoEstoqueModel> lista = repository.listAll(Sort.by("id"));
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
        if(!Copy.copyProperties(model, medicamentoEstoqueDTO)){
            return Response.status(418).build();
        }

        try{
            repository.persist(model);
        }catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status( Response.Status.CREATED.getStatusCode()).entity(model.getId()).build();
    }

    public Response update(long id, MedicamentoEstoqueDTO medicamentoEstoqueDTO){
        Set<ConstraintViolation<MedicamentoEstoqueDTO>> violations = validator.validate(medicamentoEstoqueDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        MedicamentoEstoqueModel model = repository.findById(id);
        if(model != null){
            if(!Copy.copyProperties(model, medicamentoEstoqueDTO)){
                return Response.status(418).build();
            }

            return Response.status(201).entity(model.getId()).build();
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

    public Response page(int page, int size){
        if(page < 0 || size < 1){
            return Response.status(422).build();
        }
        List<MedicamentoEstoqueModel> lista = repository.pageList(page,size);
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        PageDTO<MedicamentoEstoqueModel> pageDTO = new PageDTO<>();
        pageDTO.setLista(lista);
        pageDTO.setPages(repository.pageCount(page,size));
        pageDTO.setTotal(repository.pageTotal(page,size));
        return Response.ok(pageDTO).build();
    }

    public Response pageSort(int page, int size, String atributo, boolean asc){
        if(page < 0 || size < 1){
            return Response.status(422).build();
        }
        List<MedicamentoEstoqueModel> lista = repository.pageListSort(page,size,atributo,asc ? Sort.Direction.Ascending : Sort.Direction.Descending);
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        PageDTO<MedicamentoEstoqueModel> pageDTO = new PageDTO<>();
        pageDTO.setLista(lista);
        pageDTO.setPages(repository.pageCount(page,size));
        pageDTO.setTotal(repository.pageTotal(page,size));
        return Response.ok(pageDTO).build();
    }
}
