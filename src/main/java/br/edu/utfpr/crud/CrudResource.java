package br.edu.utfpr.crud;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public interface CrudResource<T> {

    Response getAll();

    Response getById(@PathParam("id") long id);

    Response add(T t);

    Response update(@PathParam("id") long id, T t);

    Response delete(@PathParam("id") long id);

}
