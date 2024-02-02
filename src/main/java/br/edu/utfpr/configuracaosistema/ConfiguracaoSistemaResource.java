package br.edu.utfpr.configuracaosistema;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/configuracaosistema")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "ConfiguracaoSistema")
public class ConfiguracaoSistemaResource {

    private ConfiguracaoSistemaService service;

    @Inject
    public ConfiguracaoSistemaResource(ConfiguracaoSistemaService service){
        this.service = service;
    }

    @RolesAllowed({"ADMIN","FUNCIONARIO"})
    @GET
    public Response getConfiguracaoSistema(){
        return service.find();
    }

    @RolesAllowed({"ADMIN","FUNCIONARIO"})
    @PUT
    @Path("{id}")
    @Transactional
    public Response updateConfiguracaoSistema(@PathParam("id") long id, ConfiguracaoSistemaDTO configuracaoSistemaDTO){
        return service.update(id, configuracaoSistemaDTO);
    }

}
