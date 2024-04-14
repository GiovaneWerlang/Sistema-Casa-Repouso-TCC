package br.edu.utfpr.residente;

import br.edu.utfpr.crud.CrudService;
import br.edu.utfpr.endereco.EnderecoDTO;
import br.edu.utfpr.endereco.EnderecoModel;
import br.edu.utfpr.endereco.EnderecoRepository;
import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.utils.Copy;
import br.edu.utfpr.utils.PageDTO;
import br.edu.utfpr.utils.ResponseUtils;
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
public class ResidenteService extends CrudService<ResidenteModel, ResidenteDTO, ResidenteRepository> {

    private ResidenteRepository repository;
    private EnderecoRepository enderecoRepository;
    private Validator validator;

    @Inject
    public ResidenteService(ResidenteRepository repository, EnderecoRepository enderecoRepository, Validator validator) {
        super(repository, validator);
        this.repository = repository;
        this.enderecoRepository = enderecoRepository;
        this.validator = validator;
    }

    public Response getAll(){
        List<ResidenteModel> lista = repository.listAll(Sort.by("id"));
        if(lista.isEmpty()){
            return ResponseUtils.notFound();
        }
        return ResponseUtils.okListaModel(lista);
    }

    public Response findById(long id){
        ResidenteModel model = repository.findById(id);
        if(model != null){
            return ResponseUtils.okModel(model);
        }

        return ResponseUtils.notFound();
    }

    public Response add(ResidenteDTO residenteDTO){
        Set<ConstraintViolation<ResidenteDTO>> violations = validator.validate(residenteDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        ResidenteModel model = new ResidenteModel();

        EnderecoDTO enderecoDTO = new EnderecoDTO();

        if(!Copy.copyProperties(model, residenteDTO) || !Copy.copyProperties(enderecoDTO, residenteDTO.getEndereco())){
            return ResponseUtils.porCodigo(418);
        }

        Set<ConstraintViolation<EnderecoDTO>> violationsEndereco = validator.validate(enderecoDTO);
        if(!violationsEndereco.isEmpty()){
            return ResponseError.createFromViolations(violationsEndereco).returnWithStatusCode(422);
        }
        EnderecoModel enderecoModel = new EnderecoModel();

        if(!Copy.copyProperties(enderecoModel, enderecoDTO)){
            return ResponseUtils.porCodigo(418);
        }

        try{
            enderecoRepository.persist(enderecoModel);
        }catch (Exception ex){
            return ResponseUtils.serverError();
        }

        model.setEndereco(enderecoModel);

        try{
            repository.persist(model);
        }catch (Exception ex){
            return ResponseUtils.serverError();
        }

        return ResponseUtils.criado(model.getId());
    }

    public Response update(long id, ResidenteDTO residenteDTO){
        Set<ConstraintViolation<ResidenteDTO>> violations = validator.validate(residenteDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        ResidenteModel model = repository.findById(id);
        if(model != null){

            EnderecoDTO enderecoDTO = new EnderecoDTO();

            if(!Copy.copyProperties(model, residenteDTO) || !Copy.copyProperties(enderecoDTO, residenteDTO.getEndereco())){
                return ResponseUtils.porCodigo(418);
            }

            Set<ConstraintViolation<EnderecoDTO>> violationsEndereco = validator.validate(enderecoDTO);
            if(!violationsEndereco.isEmpty()){
                return ResponseError.createFromViolations(violationsEndereco).returnWithStatusCode(422);
            }
            EnderecoModel enderecoModel;
            if(enderecoRepository.findById(residenteDTO.getEndereco().getId()) != null){
                enderecoModel = enderecoRepository.findById(residenteDTO.getEndereco().getId());
            }else{
                enderecoModel = new EnderecoModel();
            }

            if(!Copy.copyProperties(enderecoModel, enderecoDTO)){
                return ResponseUtils.porCodigo(418);
            }

            try{
                enderecoRepository.persist(enderecoModel);
            }catch (Exception ex){
                return ResponseUtils.serverError();
            }

            model.setEndereco(enderecoModel);

            try{
                repository.persist(model);
            }catch (Exception ex){
                return ResponseUtils.serverError();
            }

            return ResponseUtils.atualizadoPorCodigo(model.getId());
        }

        return ResponseUtils.notFound();
    }

    public Response delete(long id){
        ResidenteModel model = repository.findById(id);
        if(model != null){
            EnderecoModel enderecoModel = enderecoRepository.findById(model.getEndereco().getId());
            repository.delete(model);
            enderecoRepository.delete(enderecoModel);
            return ResponseUtils.okModel(model);
        }

        return ResponseUtils.notFound();
    }

}
