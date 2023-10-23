package br.edu.utfpr.entradasaida;

import br.edu.utfpr.crud.CrudService;
import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.residente.ResidenteModel;
import br.edu.utfpr.residente.ResidenteRepository;
import br.edu.utfpr.utils.PageDTO;
import io.quarkus.panache.common.Sort;
import io.quarkus.vertx.http.runtime.devmode.Json;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;


@ApplicationScoped
public class EntradaSaidaService implements CrudService<EntradaSaidaDTO> {

    private EntradaSaidaRepository repository;
    private ResidenteRepository residenteRepository;
    private Validator validator;

    @Inject
    public EntradaSaidaService(EntradaSaidaRepository repository, ResidenteRepository residenteRepository, Validator validator){
        this.repository = repository;
        this.residenteRepository = residenteRepository;
        this.validator = validator;
    }

    public Response getAll(){
        List<EntradaSaidaModel> lista = repository.listAll();
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lista).build();
    }

    public Response findById(long id){
        EntradaSaidaModel model = repository.findById(id);
        if(model != null){
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response add(EntradaSaidaDTO entradaSaidaDTO){
        Set<ConstraintViolation<EntradaSaidaDTO>> violations = validator.validate(entradaSaidaDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }
        if(entradaSaidaDTO.getDataHoraEntrada() == null && entradaSaidaDTO.getDataHoraSaida() == null){
            String message = Json.object().put("message","Data de entrada e data da saída não podem ser ambas nulas.").build();
            return Response.status(422).entity(message).type(MediaType.APPLICATION_JSON).build();
        }

        EntradaSaidaModel model = new EntradaSaidaModel();
        model.setDataHoraEntrada(entradaSaidaDTO.getDataHoraEntrada());
        model.setDataHoraSaida(entradaSaidaDTO.getDataHoraSaida());
        model.setDescricao(entradaSaidaDTO.getDescricao());

        if(residenteRepository.findById(entradaSaidaDTO.getResidente()) != null){
            ResidenteModel residenteModel = residenteRepository.findById(entradaSaidaDTO.getResidente());
            model.setResidente(residenteModel);
        }else{
            return Response.status( Response.Status.NOT_FOUND.getStatusCode(),"Residente não encontrado.").build();
        }

        try{
            repository.persist(model);
        }catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.CREATED.getStatusCode()).entity(model.getId()).build();
    }

    public Response update(long id, EntradaSaidaDTO entradaSaidaDTO){
        Set<ConstraintViolation<EntradaSaidaDTO>> violations = validator.validate(entradaSaidaDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }
        if(entradaSaidaDTO.getDataHoraEntrada() == null && entradaSaidaDTO.getDataHoraSaida() == null){
            String message = Json.object().put("message","Data de entrada e data da saída não podem ser ambas nulas.").build();
            return Response.status(422).entity(message).type(MediaType.APPLICATION_JSON).build();
        }
        EntradaSaidaModel model = repository.findById(id);
        if(model != null){
            model.setDataHoraEntrada(entradaSaidaDTO.getDataHoraEntrada());
            model.setDataHoraSaida(entradaSaidaDTO.getDataHoraSaida());
            model.setDescricao(entradaSaidaDTO.getDescricao());

            if(residenteRepository.findById(entradaSaidaDTO.getResidente()) != null){
                ResidenteModel residenteModel = residenteRepository.findById(entradaSaidaDTO.getResidente());
                model.setResidente(residenteModel);
            }else{
                return Response.status( Response.Status.NOT_FOUND.getStatusCode(),"Residente não encontrado.").build();
            }

            return Response.status(201).entity(model.getId()).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response delete(long id){
        EntradaSaidaModel model = repository.findById(id);
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
        List<EntradaSaidaModel> lista = repository.pageList(page,size);
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        PageDTO<EntradaSaidaModel> pageDTO = new PageDTO<>();
        pageDTO.setLista(lista);
        pageDTO.setPages(repository.pageCount(page,size));
        pageDTO.setTotal(repository.pageTotal(page,size));
        return Response.ok(pageDTO).build();
    }

    public Response pageSort(int page, int size, String atributo, boolean asc){
        if(page < 0 || size < 1){
            return Response.status(422).build();
        }
        List<EntradaSaidaModel> lista = repository.pageListSort(page,size,atributo,asc ? Sort.Direction.Ascending : Sort.Direction.Descending);
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        PageDTO<EntradaSaidaModel> pageDTO = new PageDTO<>();
        pageDTO.setLista(lista);
        pageDTO.setPages(repository.pageCount(page,size));
        pageDTO.setTotal(repository.pageTotal(page,size));
        return Response.ok(pageDTO).build();
    }

}
