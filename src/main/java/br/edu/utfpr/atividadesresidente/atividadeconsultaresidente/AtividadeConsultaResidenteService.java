package br.edu.utfpr.atividadesresidente.atividadeconsultaresidente;

import br.edu.utfpr.atividadesresidente.AtividadeResidenteDTO;
import br.edu.utfpr.erro.ResponseError;
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
public class AtividadeConsultaResidenteService {

    private AtividadeConsultaResidenteRepository repository;
    private Validator validator;

    @Inject
    public AtividadeConsultaResidenteService(AtividadeConsultaResidenteRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Response getAll(){
        List<AtividadeConsultaResidenteModel> lista = repository.findByTime();
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lista).build();
    }

    public Response findById(long id){
        AtividadeConsultaResidenteModel model = repository.findById(id);
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

        AtividadeConsultaResidenteModel model = repository.findById(id);
        if(model != null){
            model.setSituacao(atividadeResidenteDTO.getSituacao());

            return Response.status(201).entity(model.getId()).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response pageSort(int page, int size, String atributo, boolean asc){
        if(page < 0 || size < 1){
            return Response.status(422).build();
        }
        List<AtividadeConsultaResidenteModel> lista = repository.pageListSort(page,size,atributo,asc ? Sort.Direction.Ascending : Sort.Direction.Descending);
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        PageDTO<AtividadeConsultaResidenteModel> pageDTO = new PageDTO<>();
        pageDTO.setLista(lista);
        pageDTO.setPages(repository.pageCount(page,size));
        pageDTO.setTotal(repository.pageTotal(page,size));
        return Response.ok(pageDTO).build();
    }

}
