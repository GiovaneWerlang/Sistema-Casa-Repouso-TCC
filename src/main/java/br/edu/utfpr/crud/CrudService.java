package br.edu.utfpr.crud;

import javax.ws.rs.core.Response;

public interface CrudService<T> {

    Response getAll();

    Response findById(long id);

    Response add(T t);

    Response update(long id, T t);

    Response delete(long id);

    Response page(int page, int size);

}
