package br.edu.utfpr.crud;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

public interface CrudResource<T> {

    Response getAll(@Context SecurityContext ctx);

    Response getById(@PathParam("id") long id);

    Response add(T t);

    Response update(@PathParam("id") long id, T t);

    Response delete(@PathParam("id") long id);

    Response page(@PathParam("page") int page, @PathParam("size") int size);

}
