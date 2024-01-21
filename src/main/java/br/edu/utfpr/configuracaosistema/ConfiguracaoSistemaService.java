package br.edu.utfpr.configuracaosistema;

import br.edu.utfpr.erro.ResponseError;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import java.util.Set;

@ApplicationScoped
public class ConfiguracaoSistemaService {

    private ConfiguracaoSistemaRepository repository;
    private Validator validator;

    @Inject
    public ConfiguracaoSistemaService(ConfiguracaoSistemaRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Response find(){
        ConfiguracaoSistemaModel model = repository.findById(1L);
        if(model != null){
            model.setEmailSenha(null);
            model.setWhatsNumeroId(null);
            model.setWhatsToken(null);
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response update(long id, ConfiguracaoSistemaDTO configuracaoSistemaDTO){
        Set<ConstraintViolation<ConfiguracaoSistemaDTO>> violations = validator.validate(configuracaoSistemaDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        ConfiguracaoSistemaModel model = repository.findById(id);
        if(model != null){

            if(configuracaoSistemaDTO.isHabilitarEnvioEmail()){
                if(configuracaoSistemaDTO.getEmailLogin() == null || configuracaoSistemaDTO.getEmailSenha() == null){
                    return Response.status(422).build();
                }
            }
            if(configuracaoSistemaDTO.isHabilitarEnvioWhats()){
                if(configuracaoSistemaDTO.getWhatsNumeroId() == null || configuracaoSistemaDTO.getWhatsToken() == null){
                    return Response.status(422).build();
                }
            }

            model.setHabilitarEnvioEmail(configuracaoSistemaDTO.isHabilitarEnvioEmail());
            model.setEmailLogin(configuracaoSistemaDTO.getEmailLogin());
            model.setEmailSenha(configuracaoSistemaDTO.getEmailSenha());

            model.setHabilitarEnvioWhats(configuracaoSistemaDTO.isHabilitarEnvioWhats());
            model.setWhatsNumeroId(configuracaoSistemaDTO.getWhatsNumeroId());
            model.setWhatsToken(configuracaoSistemaDTO.getWhatsToken());

            try{
                repository.persist(model);
            }catch (Exception ex){
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }

            return Response.status(201).entity(model.getId()).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
