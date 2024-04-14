package br.edu.utfpr.usuario;

import br.edu.utfpr.crud.CrudService;
import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.profissional.ProfissionalModel;
import br.edu.utfpr.profissional.ProfissionalRepository;
import br.edu.utfpr.utils.PageDTO;
import br.edu.utfpr.utils.ResponseUtils;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@ApplicationScoped
public class UsuarioService extends CrudService<UsuarioModel, UsuarioDTO, UsuarioRepository> {
    private static final String MENSAGEMPROFISSIONAL = "Profissional não encontrado.";
    private static final String MENSAGEMUSUARIO = "Já existe um usuário com esse login.";

    private UsuarioRepository repository;
    private ProfissionalRepository profissionalRepository;
    private Validator validator;

    @Inject
    public UsuarioService(UsuarioRepository repository, ProfissionalRepository profissionalRepository, Validator validator){
        super(repository, validator);
        this.repository = repository;
        this.profissionalRepository = profissionalRepository;
        this.validator = validator;
    }

    public Response getAll(){
        List<UsuarioModel> lista = repository.listAll();
        if(lista.isEmpty()){
            return ResponseUtils.notFound();
        }
        return ResponseUtils.okListaModel(lista);
    }

    public Response findById(long id){
        UsuarioModel model = repository.findById(id);
        if(model != null){
            model.setSenha(null);
            ProfissionalModel modelProfissional = new ProfissionalModel();
            modelProfissional.setId(model.getProfissional().getId());
            model.setProfissional(modelProfissional);
            return ResponseUtils.okModel(model);
        }

        return ResponseUtils.notFound();
    }

    public Response add(UsuarioDTO usuarioDTO){
        Set<ConstraintViolation<UsuarioDTO>> violations = validator.validate(usuarioDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }
        if(repository.findByLogin(usuarioDTO.getLogin()) != null){
            return ResponseUtils.conflitoComMotivo(MENSAGEMUSUARIO);
        }
        UsuarioModel model = new UsuarioModel();
        model.setLogin(usuarioDTO.getLogin());
        model.setSenha(BcryptUtil.bcryptHash(usuarioDTO.getSenha()));
        if(profissionalRepository.findById(usuarioDTO.getProfissional()) != null){
            ProfissionalModel profissionalModel = profissionalRepository.findById(usuarioDTO.getProfissional());
            model.setProfissional(profissionalModel);
        }else{
            return ResponseUtils.notFoundComMotivo(MENSAGEMPROFISSIONAL);
        }

        try{
            repository.persist(model);
        }catch (Exception ex){
            return ResponseUtils.serverError();
        }

        return ResponseUtils.criado(model.getId());
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
                return ResponseUtils.conflitoComMotivo(MENSAGEMUSUARIO);
            }
            model.setLogin(usuarioDTO.getLogin());
            model.setSenha(BcryptUtil.bcryptHash(usuarioDTO.getSenha()));

            if(profissionalRepository.findById(usuarioDTO.getProfissional()) != null){
                ProfissionalModel profissionalModel = profissionalRepository.findById(usuarioDTO.getProfissional());
                model.setProfissional(profissionalModel);
                try{
                    repository.persist(model);
                }catch (Exception ex){
                    return ResponseUtils.serverError();
                }
            }else{
                return ResponseUtils.notFoundComMotivo(MENSAGEMPROFISSIONAL);
            }

            return ResponseUtils.atualizadoPorCodigo(model.getId());
        }

        return ResponseUtils.notFound();
    }

    public Response delete(long id){
        UsuarioModel model = repository.findById(id);
        if(model != null){
            repository.delete(model);
            return ResponseUtils.okModel(model);
        }

        return ResponseUtils.notFound();
    }

}