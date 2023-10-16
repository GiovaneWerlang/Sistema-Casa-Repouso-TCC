package br.edu.utfpr.usuario;

import br.edu.utfpr.crud.CrudService;
import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.profissional.ProfissionalModel;
import br.edu.utfpr.profissional.ProfissionalRepository;
import br.edu.utfpr.security.Security;
import br.edu.utfpr.utils.PageDTO;
import io.quarkus.elytron.security.common.BcryptUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@ApplicationScoped
public class UsuarioService implements CrudService<UsuarioDTO> {

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
        if(repository.findByLogin(usuarioDTO.getLogin()) != null){
            return Response.status(Response.Status.CONFLICT.getStatusCode(),"Já existe um usuário com esse login.").build();
        }
        UsuarioModel model = new UsuarioModel();
        model.setLogin(usuarioDTO.getLogin());
        model.setSenha(BcryptUtil.bcryptHash(usuarioDTO.getSenha()));
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

        return Response.status( Response.Status.CREATED.getStatusCode()).entity(model.getId()).build();
    }

    public Response update(long id, UsuarioDTO usuarioDTO){
        Set<ConstraintViolation<UsuarioDTO>> violations = validator.validate(usuarioDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        UsuarioModel model = repository.findById(id);
        if(model != null){
            UsuarioModel doBanco = repository.findByLogin(usuarioDTO.getLogin());
            if(doBanco != null && !Objects.equals(id,doBanco.getId())){
                return Response.status(Response.Status.CONFLICT.getStatusCode(),"Já existe um usuário com esse login.").build();
            }
            model.setLogin(usuarioDTO.getLogin());
            model.setSenha(BcryptUtil.bcryptHash(usuarioDTO.getSenha()));

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

            return Response.status(201).entity(model.getId()).build();
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

    public Response page(int page, int size){
        if(page < 0 || size < 1){
            return Response.status(422).build();
        }
        List<UsuarioModel> lista = repository.pageList(page,size);
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        PageDTO<UsuarioModel> pageDTO = new PageDTO<>();
        pageDTO.setLista(lista);
        pageDTO.setPages(repository.pageCount(page,size));
        pageDTO.setTotal(repository.pageTotal(page,size));
        return Response.ok(pageDTO).build();
    }

}