package br.edu.utfpr.atividadesresidente.atividademedicamentoresidente;

import br.edu.utfpr.atividadesresidente.AtividadeResidenteDTO;
import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.profissional.ProfissionalModel;
import br.edu.utfpr.profissional.ProfissionalRepository;
import br.edu.utfpr.utils.PageDTO;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class AtividadeMedicamentoResidenteService {

    private AtividadeMedicamentoResidenteRepository repository;
    private Validator validator;
    private ProfissionalRepository profissionalRepository;

    @Inject
    public AtividadeMedicamentoResidenteService(AtividadeMedicamentoResidenteRepository repository, Validator validator, ProfissionalRepository profissionalRepository) {
        this.repository = repository;
        this.validator = validator;
        this.profissionalRepository = profissionalRepository;
    }

    public Response getAll(){
        List<AtividadeMedicamentoResidenteModel> lista = repository.findByTime();
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lista).build();
    }

    public Response findById(long id){
        AtividadeMedicamentoResidenteModel model = repository.findById(id);
        if(model != null){
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response update(long id, AtividadeResidenteDTO atividadeResidenteDTO){
        Set<ConstraintViolation<AtividadeResidenteDTO>> violations = validator.validate(atividadeResidenteDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        AtividadeMedicamentoResidenteModel model = repository.findById(id);
        ProfissionalModel profissionalModel = profissionalRepository.findById(atividadeResidenteDTO.getProfissional());
        if(model != null && profissionalModel != null){
            model.setSituacao(atividadeResidenteDTO.getSituacao());
            model.setProfissional(profissionalModel);

            return Response.status(201).entity(model.getId()).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response pageSort(int page, int size, String atributo, boolean asc){
        if(page < 0 || size < 1){
            return Response.status(422).build();
        }
        List<AtividadeMedicamentoResidenteModel> lista = repository.pageListSort(page,size,atributo,asc ? Sort.Direction.Ascending : Sort.Direction.Descending);
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        PageDTO<AtividadeMedicamentoResidenteModel> pageDTO = new PageDTO<>();
        pageDTO.setLista(lista);
        pageDTO.setPages(repository.pageCount(page,size));
        pageDTO.setTotal(repository.pageTotal(page,size));
        return Response.ok(pageDTO).build();
    }

}
