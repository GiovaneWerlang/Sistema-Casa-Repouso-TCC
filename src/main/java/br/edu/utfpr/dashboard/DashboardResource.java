package br.edu.utfpr.dashboard;

import jakarta.annotation.security.RolesAllowed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@Path("/dashboard")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Dashboard")
public class DashboardResource {

    private DashboardService service;

    @Inject
    public DashboardResource(DashboardService service){
        this.service = service;
    }

    @Operation(summary = "Retorna todos")
    @RolesAllowed({"ADMIN","CUIDADOR","ENFERMEIRO","FUNCIONARIO","MEDICO","VOLUNTARIO"})
    @GET
    public Response getAll(@Context SecurityContext ctx){
        return service.getAll();
    }

}
