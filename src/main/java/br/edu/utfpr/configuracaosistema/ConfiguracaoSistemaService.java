package br.edu.utfpr.configuracaosistema;

import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.utils.ResponseUtils;

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
            return ResponseUtils.okModel(model);
        }

        return ResponseUtils.notFound();
    }

    public Response update(long id, ConfiguracaoSistemaDTO configuracaoSistemaDTO){
        Set<ConstraintViolation<ConfiguracaoSistemaDTO>> violations = validator.validate(configuracaoSistemaDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        ConfiguracaoSistemaModel model = repository.findById(id);
        if(model != null){

            if(validaCamposEmailInvalidos(configuracaoSistemaDTO)){
                return ResponseUtils.porCodigo(422);
            }
            if(validaCamposWhatsappInvalidos(configuracaoSistemaDTO)){
                return ResponseUtils.porCodigo(422);
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
                return ResponseUtils.serverError();
            }

            return ResponseUtils.atualizadoPorCodigo(model.getId());
        }

        return ResponseUtils.notFound();
    }

    private boolean validaCamposEmailInvalidos(ConfiguracaoSistemaDTO configuracaoSistemaDTO){
        return configuracaoSistemaDTO.isHabilitarEnvioEmail() && (configuracaoSistemaDTO.getEmailLogin() == null || configuracaoSistemaDTO.getEmailSenha() == null);
    }

    private boolean validaCamposWhatsappInvalidos(ConfiguracaoSistemaDTO configuracaoSistemaDTO){
        return configuracaoSistemaDTO.isHabilitarEnvioWhats() && (configuracaoSistemaDTO.getWhatsNumeroId() == null || configuracaoSistemaDTO.getWhatsToken() == null);
    }

}
