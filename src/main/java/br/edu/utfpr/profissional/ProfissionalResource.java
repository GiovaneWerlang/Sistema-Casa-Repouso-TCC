package br.edu.utfpr.profissional;

import br.edu.utfpr.endereco.EnderecoDTO;
import br.edu.utfpr.endereco.EnderecoModel;
import br.edu.utfpr.endereco.EnderecoRepository;
import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.especialidade.EspecialidadeModel;
import br.edu.utfpr.especialidade.EspecialidadeRepository;
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

@Path("/profissional")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Profissional")
public class ProfissionalResource {

    private ProfissionalService service;

    @Inject
    public ProfissionalResource(ProfissionalService service){
        this.service = service;
    }

    @Operation(summary = "Retorna todos")
    @GET
    public Response getAll(){
        return service.getAll();
    }

    @GET
    @Path("{id}")
    public Response getProfissionalById(@PathParam("id") long id){
        return service.findById(id);
    }
    @POST
    @Transactional
    public Response addProfissional(ProfissionalDTO profissionalDTO){
        return service.add(profissionalDTO);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateProfissional(@PathParam("id") long id, ProfissionalDTO profissionalDTO){
        return service.update(id, profissionalDTO);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteProfissional(@PathParam("id") long id){
        return service.delete(id);
    }
}
