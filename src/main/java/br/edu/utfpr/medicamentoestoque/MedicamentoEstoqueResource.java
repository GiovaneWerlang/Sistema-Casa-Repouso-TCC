package br.edu.utfpr.medicamentoestoque;

import br.edu.utfpr.erro.ResponseError;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@Path("/medicamentoestoque")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Medicamento Estoque")
public class MedicamentoEstoqueResource {

    private MedicamentoEstoqueRepository repository;
    private Validator validator;

    @Inject
    public MedicamentoEstoqueResource(MedicamentoEstoqueRepository repository, Validator validator){
        this.repository = repository;
        this.validator = validator;
    }

    @Operation(summary = "Retorna todos")
    @GET
    public Response getAll(){
        List<MedicamentoEstoqueModel> lista = repository.listAll();
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lista).build();
    }

    @GET
    @Path("{id}")
    public Response getMedicamentoEstoqueById(@PathParam("id") long id){
        MedicamentoEstoqueModel model = repository.findById(id);
        if(model != null){
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @POST
    @Transactional
    public Response addMedicamentoEstoque(MedicamentoEstoqueDTO enderecoDTO){
        Set<ConstraintViolation<MedicamentoEstoqueDTO>> violations = validator.validate(enderecoDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        MedicamentoEstoqueModel model = new MedicamentoEstoqueModel();
        model.setNome(enderecoDTO.getNome());
        model.setPrincipioAtivo(enderecoDTO.getPrincipioAtivo());
        model.setQtde(enderecoDTO.getQtde());

        try{
            repository.persist(model);
        }catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status( Response.Status.CREATED.getStatusCode(),model.toString()).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateMedicamentoEstoque(@PathParam("id") long id, MedicamentoEstoqueDTO enderecoDTO){
        Set<ConstraintViolation<MedicamentoEstoqueDTO>> violations = validator.validate(enderecoDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        MedicamentoEstoqueModel model = repository.findById(id);
        if(model != null){
            model.setNome(enderecoDTO.getNome());
            model.setPrincipioAtivo(enderecoDTO.getPrincipioAtivo());
            model.setQtde(enderecoDTO.getQtde());

            return Response.status(201, model.toString()).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteMedicamentoEstoque(@PathParam("id") long id){
        MedicamentoEstoqueModel model = repository.findById(id);
        if(model != null){
            repository.delete(model);
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
