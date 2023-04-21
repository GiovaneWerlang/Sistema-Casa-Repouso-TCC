package br.edu.utfpr.usuario;

import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.profissional.ProfissionalModel;
import br.edu.utfpr.profissional.ProfissionalRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;


@ApplicationScoped
public class UsuarioService {

    private UsuarioRepository repository;
    private ProfissionalRepository profissionalRepository;
    private Validator validator;

    @Inject
    public UsuarioService(UsuarioRepository repository, ProfissionalRepository profissionalRepository, Validator validator){
        this.repository = repository;
        this.profissionalRepository = profissionalRepository;
        this.validator = validator;
    }

    public Response getAll(){
        List<UsuarioModel> lista = repository.listAll();
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lista).build();
    }

    public Response findById(long id){
        UsuarioModel model = repository.findById(id);
        if(model != null){
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response add(UsuarioDTO usuarioDTO){
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

    public Response update(long id, UsuarioDTO usuarioDTO){
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

    public Response delete(long id){
        UsuarioModel model = repository.findById(id);
        if(model != null){
            repository.delete(model);
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}