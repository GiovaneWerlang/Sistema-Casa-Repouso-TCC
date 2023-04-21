package br.edu.utfpr.profissional;

import br.edu.utfpr.endereco.EnderecoDTO;
import br.edu.utfpr.endereco.EnderecoModel;
import br.edu.utfpr.endereco.EnderecoRepository;
import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.especialidade.EspecialidadeModel;
import br.edu.utfpr.especialidade.EspecialidadeRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;


@ApplicationScoped
public class ProfissionalService {

    private ProfissionalRepository repository;
    private EnderecoRepository enderecoRepository;
    private EspecialidadeRepository especialidadeRepository;
    private Validator validator;

    @Inject
    public ProfissionalService(ProfissionalRepository repository, EnderecoRepository enderecoRepository, EspecialidadeRepository especialidadeRepository, Validator validator){
        this.repository = repository;
        this.enderecoRepository = enderecoRepository;
        this.especialidadeRepository = especialidadeRepository;
        this.validator = validator;
    }

    public Response getAll(){
        List<ProfissionalModel> lista = repository.listAll();
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lista).build();
    }

    public Response findById(long id){
        ProfissionalModel model = repository.findById(id);
        if(model != null){
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response add(ProfissionalDTO profissionalDTO){
        Set<ConstraintViolation<ProfissionalDTO>> violations = validator.validate(profissionalDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        ProfissionalModel model = new ProfissionalModel();
        model.setNome(profissionalDTO.getNome());
        model.setIdade(profissionalDTO.getIdade());
        model.setCpf(profissionalDTO.getCpf());
        model.setTelefone(profissionalDTO.getTelefone());
        model.setEmail(profissionalDTO.getEmail());
        model.setDataAdmissao(profissionalDTO.getDataAdmissao());
        model.setSalario(profissionalDTO.getSalario());
        model.setSituacao(profissionalDTO.getSituacao());
        model.setFuncao(profissionalDTO.getFuncao());

        EspecialidadeModel especialidadeModel;
        if(especialidadeRepository.findById(profissionalDTO.getEspecialidade()) != null){
            especialidadeModel = especialidadeRepository.findById(profissionalDTO.getEspecialidade());
        }else{
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Especialidade não encontrada.").build();
        }
        model.setEspecialidade(especialidadeModel);

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setLogradouro(profissionalDTO.getEndereco().getLogradouro());
        enderecoDTO.setNumero(profissionalDTO.getEndereco().getNumero());
        enderecoDTO.setBairro(profissionalDTO.getEndereco().getBairro());
        enderecoDTO.setMunicipio(profissionalDTO.getEndereco().getMunicipio());
        enderecoDTO.setCep(profissionalDTO.getEndereco().getCep());
        enderecoDTO.setEstado(profissionalDTO.getEndereco().getEstado());
        enderecoDTO.setPais(profissionalDTO.getEndereco().getPais());

        Set<ConstraintViolation<EnderecoDTO>> violationsEndereco = validator.validate(enderecoDTO);
        if(!violationsEndereco.isEmpty()){
            return ResponseError.createFromViolations(violationsEndereco).returnWithStatusCode(422);
        }
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setLogradouro(profissionalDTO.getEndereco().getLogradouro());
        enderecoModel.setNumero(profissionalDTO.getEndereco().getNumero());
        enderecoModel.setBairro(profissionalDTO.getEndereco().getBairro());
        enderecoModel.setMunicipio(profissionalDTO.getEndereco().getMunicipio());
        enderecoModel.setCep(profissionalDTO.getEndereco().getCep());
        enderecoModel.setEstado(profissionalDTO.getEndereco().getEstado());
        enderecoModel.setPais(profissionalDTO.getEndereco().getPais());

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

    public Response update(long id, ProfissionalDTO profissionalDTO){
        Set<ConstraintViolation<ProfissionalDTO>> violations = validator.validate(profissionalDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        ProfissionalModel model = repository.findById(id);
        if(model != null){
            model.setNome(profissionalDTO.getNome());
            model.setIdade(profissionalDTO.getIdade());
            model.setCpf(profissionalDTO.getCpf());
            model.setTelefone(profissionalDTO.getTelefone());
            model.setEmail(profissionalDTO.getEmail());
            model.setSituacao(profissionalDTO.getSituacao());
            model.setDataAdmissao(profissionalDTO.getDataAdmissao());
            model.setSalario(profissionalDTO.getSalario());
            model.setSituacao(profissionalDTO.getSituacao());
            model.setFuncao(profissionalDTO.getFuncao());

            EspecialidadeModel especialidadeModel;
            if(especialidadeRepository.findById(profissionalDTO.getEspecialidade()) != null){
                especialidadeModel = especialidadeRepository.findById(profissionalDTO.getEspecialidade());
            }else{
                return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Especialidade não encontrada.").build();
            }
            model.setEspecialidade(especialidadeModel);

            EnderecoDTO enderecoDTO = new EnderecoDTO();
            enderecoDTO.setLogradouro(profissionalDTO.getEndereco().getLogradouro());
            enderecoDTO.setNumero(profissionalDTO.getEndereco().getNumero());
            enderecoDTO.setBairro(profissionalDTO.getEndereco().getBairro());
            enderecoDTO.setMunicipio(profissionalDTO.getEndereco().getMunicipio());
            enderecoDTO.setCep(profissionalDTO.getEndereco().getCep());
            enderecoDTO.setEstado(profissionalDTO.getEndereco().getEstado());
            enderecoDTO.setPais(profissionalDTO.getEndereco().getPais());

            Set<ConstraintViolation<EnderecoDTO>> violationsEndereco = validator.validate(enderecoDTO);
            if(!violationsEndereco.isEmpty()){
                return ResponseError.createFromViolations(violationsEndereco).returnWithStatusCode(422);
            }
            EnderecoModel enderecoModel;
            if(enderecoRepository.findById(profissionalDTO.getEndereco().getId()) != null){
                enderecoModel = enderecoRepository.findById(profissionalDTO.getEndereco().getId());
            }else{
                enderecoModel = new EnderecoModel();
            }

            enderecoModel.setLogradouro(profissionalDTO.getEndereco().getLogradouro());
            enderecoModel.setNumero(profissionalDTO.getEndereco().getNumero());
            enderecoModel.setBairro(profissionalDTO.getEndereco().getBairro());
            enderecoModel.setMunicipio(profissionalDTO.getEndereco().getMunicipio());
            enderecoModel.setCep(profissionalDTO.getEndereco().getCep());
            enderecoModel.setEstado(profissionalDTO.getEndereco().getEstado());
            enderecoModel.setPais(profissionalDTO.getEndereco().getPais());
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

    public Response delete(long id){
        ProfissionalModel model = repository.findById(id);
        if(model != null){
            EnderecoModel enderecoModel = enderecoRepository.findById(model.getEndereco().getId());
            repository.delete(model);
            enderecoRepository.delete(enderecoModel);
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
