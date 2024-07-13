package br.edu.utfpr.crud;

import br.edu.utfpr.utils.PageDTO;
import br.edu.utfpr.utils.ResponseUtils;
import io.quarkus.panache.common.Sort;

import jakarta.validation.Validator;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;

public abstract class CrudService<T, D, R extends CrudRepository<T>> {

    private R repository;
    private Validator validator;

    public CrudService(R repository, Validator validator){
        this.repository = repository;
        this.validator = validator;
    }

    public CrudService(){
    }

    public R getRepository() {
        return repository;
    }

    public Validator getValidator() {
        return validator;
    }

    public abstract Response getAll();

    public abstract Response findById(long id);

    public abstract Response add(D t);

    public abstract Response update(long id, D t);

    public abstract Response delete(long id);

    public Response pageSort(int page, int size, String atributo, boolean asc){
        if(page < 0 || size < 1){
            return ResponseUtils.porCodigo(422);
        }
        List<T> lista = repository.pageListSort(page,size,atributo,asc ? Sort.Direction.Ascending : Sort.Direction.Descending);
        if(Objects.isNull(lista)){
            return ResponseUtils.notFound();
        }
        PageDTO<T> pageDTO = new PageDTO<>();
        pageDTO.setLista(lista);
        pageDTO.setPages(repository.pageCount(page,size));
        pageDTO.setTotal(repository.pageTotal(page,size));
        return ResponseUtils.okPage(pageDTO);
    }

}
