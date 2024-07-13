package br.edu.utfpr.utils;

import io.quarkus.vertx.http.runtime.devmode.Json;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

public class ResponseUtils {

    private ResponseUtils(){}

    public static Response notFound(){
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public static Response notFoundComMotivo(String motivo){
        return Response.status(Response.Status.NOT_FOUND.getStatusCode(), motivo).build();
    }

    public static Response notModifiedComMotivo(String motivo){
        return Response.status(Response.Status.NOT_MODIFIED.getStatusCode(), motivo).build();
    }

    public static Response conflitoComMotivo(String motivo){
        return Response.status(Response.Status.CONFLICT.getStatusCode(), motivo).build();
    }

    public static Response notAuth(){
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    public static Response inconsistenciaComMotivo(String motivo){
        return Response.status(422, motivo).build();
    }

    public static Response inconsistenciaComObjeto(String motivo){
        return Response.status(422).entity(Json.object().put("message",motivo).build()).type(MediaType.APPLICATION_JSON).build();
    }

    public static Response serverError(){
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    public static Response porCodigo(int codigo){
        return Response.status(codigo).build();
    }

    public static Response criado(Long id){
        return Response.status(Response.Status.CREATED.getStatusCode()).entity(id).build();
    }

    public static Response atualizadoPorCodigo(Long id){
        return Response.status(201).entity(id).build();
    }

    public static Response okModel(Object model){
        return Response.ok(model).build();
    }

    public static Response okListaModel(List<?> model){
        return Response.ok(model).build();
    }

    public static Response okPage(PageDTO<?> model){
        return Response.ok(model).build();
    }

    public static Response ok(){
        return Response.ok().build();
    }
}
