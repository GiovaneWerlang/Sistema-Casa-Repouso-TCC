package br.edu.utfpr.usuario;


import br.edu.utfpr.profissional.ProfissionalModel;
import br.edu.utfpr.usuario.UsuarioDTO;
import br.edu.utfpr.usuario.UsuarioModel;
import br.edu.utfpr.usuario.UsuarioRepository;
import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.profissional.ProfissionalRepository;
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

@Path("/usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Usuário")
public class UsuarioResource {

    private UsuarioRepository repository;
    private ProfissionalRepository profissionalRepository;
    private Validator validator;

    @Inject
    public UsuarioResource(UsuarioRepository repository, ProfissionalRepository profissionalRepository, Validator validator){
        this.repository = repository;
        this.profissionalRepository = profissionalRepository;
        this.validator = validator;
    }

    @Operation(summary = "Retorna todos")
    @GET
    public Response getAll(){
        List<UsuarioModel> lista = repository.listAll();
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lista).build();
    }

    @GET
    @Path("{id}")
    public Response getUsuarioById(@PathParam("id") long id){
        UsuarioModel model = repository.findById(id);
        if(model != null){
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @POST
    @Transactional
    public Response addUsuario(UsuarioDTO usuarioDTO){
        Set<ConstraintViolation<UsuarioDTO>> violations = validator.validate(usuarioDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        UsuarioModel model = new UsuarioModel();
        model.setLogin(usuarioDTO.getLogin());
        model.setSenha(usuarioDTO.getSenha());
        if(profissionalRepository.findById(usuarioDTO.getProfissional()) != null){
            ProfissionalModel profissionalModel = profissionalRepository.findById(usuarioDTO.getProfissional());
            model.setProfissional(profissionalModel);
        }else{
            return Response.status( Response.Status.NOT_FOUND.getStatusCode(),"Profissional não encontrado.").build();
        }

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
    public Response updateUsuario(@PathParam("id") long id, UsuarioDTO usuarioDTO){
        Set<ConstraintViolation<UsuarioDTO>> violations = validator.validate(usuarioDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        UsuarioModel model = repository.findById(id);
        if(model != null){
            model.setLogin(usuarioDTO.getLogin());
            model.setSenha(usuarioDTO.getSenha());

            if(profissionalRepository.findById(usuarioDTO.getProfissional()) != null){
                ProfissionalModel profissionalModel = profissionalRepository.findById(usuarioDTO.getProfissional());
                model.setProfissional(profissionalModel);
                try{
                    repository.persist(model);
                }catch (Exception ex){
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                }
            }else{
                return Response.status( Response.Status.NOT_FOUND.getStatusCode(),"Profissional não encontrado.").build();
            }

            return Response.status(201, model.toString()).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteUsuario(@PathParam("id") long id){
        UsuarioModel model = repository.findById(id);
        if(model != null){
            repository.delete(model);
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
