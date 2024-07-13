package br.edu.utfpr.crud;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

public interface CrudResource<T> {

    Response getAll(@Context SecurityContext ctx);

    Response getById(@PathParam("id") long id);

    Response add(T t);

    Response update(@PathParam("id") long id, T t);

    Response delete(@PathParam("id") long id);

    Response pageSort(@PathParam("page") int page, @PathParam("size") int size, @PathParam("sort") String sort,@PathParam("asc") boolean asc);

}
