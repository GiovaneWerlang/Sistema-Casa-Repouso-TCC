package br.edu.utfpr.residente;

import br.edu.utfpr.endereco.EnderecoDTO;
import br.edu.utfpr.endereco.EnderecoModel;
import br.edu.utfpr.endereco.EnderecoRepository;
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

@Path("/residente")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Residente")
public class ResidenteResource {

    private ResidenteRepository repository;
    private EnderecoRepository enderecoRepository;
    private Validator validator;

    @Inject
    public ResidenteResource(ResidenteRepository repository, EnderecoRepository enderecoRepository, Validator validator){
        this.repository = repository;
        this.enderecoRepository = enderecoRepository;
        this.validator = validator;
    }

    @Operation(summary = "Retorna todos")
    @GET
    public Response getAll(){
        List<ResidenteModel> lista = repository.listAll();
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lista).build();
    }

    @GET
    @Path("{id}")
    public Response getResidenteById(@PathParam("id") long id){
        ResidenteModel model = repository.findById(id);
        if(model != null){
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Transactional
    public Response addResidente(ResidenteDTO residenteDTO){
        Set<ConstraintViolation<ResidenteDTO>> violations = validator.validate(residenteDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        ResidenteModel model = new ResidenteModel();
        model.setNome(residenteDTO.getNome());
        model.setIdade(residenteDTO.getIdade());
        model.setCpf(residenteDTO.getCpf());
        model.setTelefone(residenteDTO.getTelefone());
        model.setEmail(residenteDTO.getEmail());
        model.setSituacao(residenteDTO.getSituacao());
        model.setTipoEstadia(residenteDTO.getTipoEstadia());
        model.setDataHoraIngresso(residenteDTO.getDataHoraIngresso());
        model.setDataHoraPrevisaoSaida(residenteDTO.getDataHoraPrevisaoSaida());

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setLogradouro(residenteDTO.getEndereco().getLogradouro());
        enderecoDTO.setNumero(residenteDTO.getEndereco().getNumero());
        enderecoDTO.setBairro(residenteDTO.getEndereco().getBairro());
        enderecoDTO.setMunicipio(residenteDTO.getEndereco().getMunicipio());
        enderecoDTO.setCep(residenteDTO.getEndereco().getCep());
        enderecoDTO.setEstado(residenteDTO.getEndereco().getEstado());
        enderecoDTO.setPais(residenteDTO.getEndereco().getPais());

        Set<ConstraintViolation<EnderecoDTO>> violationsEndereco = validator.validate(enderecoDTO);
        if(!violationsEndereco.isEmpty()){
            return ResponseError.createFromViolations(violationsEndereco).returnWithStatusCode(422);
        }
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setLogradouro(residenteDTO.getEndereco().getLogradouro());
        enderecoModel.setNumero(residenteDTO.getEndereco().getNumero());
        enderecoModel.setBairro(residenteDTO.getEndereco().getBairro());
        enderecoModel.setMunicipio(residenteDTO.getEndereco().getMunicipio());
        enderecoModel.setCep(residenteDTO.getEndereco().getCep());
        enderecoModel.setEstado(residenteDTO.getEndereco().getEstado());
        enderecoModel.setPais(residenteDTO.getEndereco().getPais());

        try{
            enderecoRepository.persist(enderecoModel);
        }catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        model.setEndereco(enderecoModel);

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
    public Response updateResidente(@PathParam("id") long id, ResidenteDTO residenteDTO){
        Set<ConstraintViolation<ResidenteDTO>> violations = validator.validate(residenteDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        ResidenteModel model = repository.findById(id);
        if(model != null){
            model.setNome(residenteDTO.getNome());
            model.setIdade(residenteDTO.getIdade());
            model.setCpf(residenteDTO.getCpf());
            model.setTelefone(residenteDTO.getTelefone());
            model.setEmail(residenteDTO.getEmail());
            model.setSituacao(residenteDTO.getSituacao());
            model.setTipoEstadia(residenteDTO.getTipoEstadia());
            model.setDataHoraIngresso(residenteDTO.getDataHoraIngresso());
            model.setDataHoraPrevisaoSaida(residenteDTO.getDataHoraPrevisaoSaida());

            EnderecoDTO enderecoDTO = new EnderecoDTO();
            enderecoDTO.setLogradouro(residenteDTO.getEndereco().getLogradouro());
            enderecoDTO.setNumero(residenteDTO.getEndereco().getNumero());
            enderecoDTO.setBairro(residenteDTO.getEndereco().getBairro());
            enderecoDTO.setMunicipio(residenteDTO.getEndereco().getMunicipio());
            enderecoDTO.setCep(residenteDTO.getEndereco().getCep());
            enderecoDTO.setEstado(residenteDTO.getEndereco().getEstado());
            enderecoDTO.setPais(residenteDTO.getEndereco().getPais());

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

            enderecoModel.setLogradouro(residenteDTO.getEndereco().getLogradouro());
            enderecoModel.setNumero(residenteDTO.getEndereco().getNumero());
            enderecoModel.setBairro(residenteDTO.getEndereco().getBairro());
            enderecoModel.setMunicipio(residenteDTO.getEndereco().getMunicipio());
            enderecoModel.setCep(residenteDTO.getEndereco().getCep());
            enderecoModel.setEstado(residenteDTO.getEndereco().getEstado());
            enderecoModel.setPais(residenteDTO.getEndereco().getPais());

            try{
                enderecoRepository.persist(enderecoModel);
            }catch (Exception ex){
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }

            model.setEndereco(enderecoModel);

            try{
                repository.persist(model);
            }catch (Exception ex){
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }

            return Response.status(201, model.toString()).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteResidente(@PathParam("id") long id){
        ResidenteModel model = repository.findById(id);
        if(model != null){
            EnderecoModel enderecoModel = enderecoRepository.findById(model.getEndereco().getId());
            repository.delete(model);
            enderecoRepository.delete(enderecoModel);
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
