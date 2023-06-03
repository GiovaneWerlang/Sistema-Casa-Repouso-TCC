package br.edu.utfpr.medicamentoestoque;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/medicamentoestoque")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Medicamento Estoque")
public class MedicamentoEstoqueResource {

    private MedicamentoEstoqueService service;

    @Inject
    public MedicamentoEstoqueResource(MedicamentoEstoqueService service){
        this.service = service;
    }

    @Operation(summary = "Retorna todos")
    @GET
    public Response getAll(){
        return service.getAll();
    }

    @GET
    @Path("{id}")
    public Response getMedicamentoEstoqueById(@PathParam("id") long id){
        return service.findById(id);
    }
    @POST
    @Transactional
    public Response addMedicamentoEstoque(MedicamentoEstoqueDTO medicamentoEstoqueDTO){
       return service.add(medicamentoEstoqueDTO);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateMedicamentoEstoque(@PathParam("id") long id, MedicamentoEstoqueDTO medicamentoEstoqueDTO){
       return service.update(id, medicamentoEstoqueDTO);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteMedicamentoEstoque(@PathParam("id") long id){
       return service.delete(id);
    }
}
