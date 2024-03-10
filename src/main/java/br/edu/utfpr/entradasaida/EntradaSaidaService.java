package br.edu.utfpr.entradasaida;

import br.edu.utfpr.crud.CrudService;
import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.residente.ResidenteModel;
import br.edu.utfpr.residente.ResidenteRepository;
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
public class EntradaSaidaService implements CrudService<EntradaSaidaDTO> {

    private static final String MENSAGEMDATAHORA = "Data de entrada e data da saída não podem ser ambas nulas.";
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
            return ResponseUtils.notFound();
        }
        return ResponseUtils.okListaModel(lista);
    }

    public Response findById(long id){
        EntradaSaidaModel model = repository.findById(id);
        if(model != null){
            return ResponseUtils.okModel(model);
        }

        return ResponseUtils.notFound();
    }

    public Response add(EntradaSaidaDTO entradaSaidaDTO){
        Set<ConstraintViolation<EntradaSaidaDTO>> violations = validator.validate(entradaSaidaDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }
        if(entradaSaidaDTO.getDataHoraEntrada() == null && entradaSaidaDTO.getDataHoraSaida() == null){
            return ResponseUtils.inconsistenciaComObjeto(MENSAGEMDATAHORA);
        }

        EntradaSaidaModel model = new EntradaSaidaModel();
        model.setDataHoraEntrada(entradaSaidaDTO.getDataHoraEntrada());
        model.setDataHoraSaida(entradaSaidaDTO.getDataHoraSaida());
        model.setDescricao(entradaSaidaDTO.getDescricao());

        if(residenteRepository.findById(entradaSaidaDTO.getResidente()) != null){
            ResidenteModel residenteModel = residenteRepository.findById(entradaSaidaDTO.getResidente());
            model.setResidente(residenteModel);
        }else{
            return ResponseUtils.notFoundComMotivo("Residente não encontrado.");
        }

        try{
            repository.persist(model);
        }catch (Exception ex){
            return ResponseUtils.serverError();
        }
        return ResponseUtils.criado(model.getId());
    }

    public Response update(long id, EntradaSaidaDTO entradaSaidaDTO){
        Set<ConstraintViolation<EntradaSaidaDTO>> violations = validator.validate(entradaSaidaDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }
        if(entradaSaidaDTO.getDataHoraEntrada() == null && entradaSaidaDTO.getDataHoraSaida() == null){
            return ResponseUtils.inconsistenciaComObjeto(MENSAGEMDATAHORA);
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
                return ResponseUtils.notFoundComMotivo("Residente não encontrado.");
            }

            return ResponseUtils.atualizadoPorCodigo(model.getId());
        }

        return ResponseUtils.notFound();
    }

    public Response delete(long id){
        EntradaSaidaModel model = repository.findById(id);
        if(model != null){
            repository.delete(model);
            return ResponseUtils.okModel(model);
        }

        return ResponseUtils.notFound();
    }

    public Response page(int page, int size){
        if(page < 0 || size < 1){
            return ResponseUtils.porCodigo(422);
        }
        List<EntradaSaidaModel> lista = repository.pageList(page,size);
        if(Objects.isNull(lista)){
            return ResponseUtils.notFound();
        }
        PageDTO<EntradaSaidaModel> pageDTO = new PageDTO<>();
        pageDTO.setLista(lista);
        pageDTO.setPages(repository.pageCount(page,size));
        pageDTO.setTotal(repository.pageTotal(page,size));
        return ResponseUtils.okPage(pageDTO);
    }

    public Response pageSort(int page, int size, String atributo, boolean asc){
        if(page < 0 || size < 1){
            return ResponseUtils.porCodigo(422);
        }
        List<EntradaSaidaModel> lista = repository.pageListSort(page,size,atributo,asc ? Sort.Direction.Ascending : Sort.Direction.Descending);
        if(Objects.isNull(lista)){
            return ResponseUtils.notFound();
        }
        PageDTO<EntradaSaidaModel> pageDTO = new PageDTO<>();
        pageDTO.setLista(lista);
        pageDTO.setPages(repository.pageCount(page,size));
        pageDTO.setTotal(repository.pageTotal(page,size));
        return ResponseUtils.okPage(pageDTO);
    }

}
