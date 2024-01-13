package br.edu.utfpr.medicamentouso;

import br.edu.utfpr.atividadesresidente.atividademedicamentoresidente.AtividadeMedicamentoResidenteRepository;
import br.edu.utfpr.crud.CrudService;
import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.medicamentoestoque.MedicamentoEstoqueModel;
import br.edu.utfpr.medicamentoestoque.MedicamentoEstoqueRepository;
import br.edu.utfpr.residente.ResidenteModel;
import br.edu.utfpr.residente.ResidenteRepository;
import br.edu.utfpr.utils.PageDTO;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@ApplicationScoped
public class MedicamentoUsoService implements CrudService<MedicamentoUsoDTO> {

    private MedicamentoUsoRepository repository;
    private ResidenteRepository residenteRepository;
    private MedicamentoEstoqueRepository medicamentoEstoqueRepository;
    private AtividadeMedicamentoResidenteRepository atividadeMedicamentoResidenteRepository;
    private Validator validator;

    @Inject
    public MedicamentoUsoService(
            MedicamentoUsoRepository repository,
            ResidenteRepository residenteRepository,
            MedicamentoEstoqueRepository medicamentoEstoqueRepository,
            AtividadeMedicamentoResidenteRepository atividadeMedicamentoResidenteRepository,
            Validator validator) {
        this.repository = repository;
        this.residenteRepository = residenteRepository;
        this.medicamentoEstoqueRepository = medicamentoEstoqueRepository;
        this.atividadeMedicamentoResidenteRepository = atividadeMedicamentoResidenteRepository;
        this.validator = validator;
    }

    public Response getAll(){
        List<MedicamentoUsoModel> lista = repository.listAll();
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lista).build();
    }

    public Response findById(long id){
        MedicamentoUsoModel model = repository.findById(id);
        if(model != null){
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response add(MedicamentoUsoDTO medicamentoUsoDTO){
        Set<ConstraintViolation<MedicamentoUsoDTO>> violations = validator.validate(medicamentoUsoDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        MedicamentoUsoModel model = new MedicamentoUsoModel();
        model.setIntervalo(medicamentoUsoDTO.getIntervalo());
        model.setQtdeVezesAoDia(medicamentoUsoDTO.getQtdeVezesAoDia());
        model.setDataHoraInicio(medicamentoUsoDTO.getDataHoraInicio());
        model.setQtdeDiasUso(medicamentoUsoDTO.getQtdeDiasUso());
        model.setQtdeMedicamento(medicamentoUsoDTO.getQtdeMedicamento());

        ResidenteModel residenteModel;
        Optional<ResidenteModel> optional = residenteRepository.findByIdOptional(medicamentoUsoDTO.getResidente());
        if(optional.isPresent()){
            residenteModel = optional.get();
        }else{
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Residente não encontrado(a).").build();
        }
        model.setResidente(residenteModel);

        MedicamentoEstoqueModel medicamentoEstoqueModel;
        Optional<MedicamentoEstoqueModel> optionalMedicamentoEstoque = medicamentoEstoqueRepository.findByIdOptional(medicamentoUsoDTO.getMedicamento());
        if(optional.isPresent()){
            medicamentoEstoqueModel = optionalMedicamentoEstoque.get();
        }else{
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Medicamento não encontrado(a).").build();
        }
        model.setMedicamento(medicamentoEstoqueModel);

        if(model.getIntervalo() < 24 && model.getQtdeVezesAoDia() * model.getIntervalo() > 24 || model.getIntervalo() < 1){
            return Response.status(422, "Inconsistência nos dados informados.").build();
        }
        if(model.getIntervalo() >= 24 && model.getQtdeVezesAoDia() > 1){
            return Response.status(422, "O campo vezes ao dia não pode ter valor maior que 1 se o intervalo for igual ou maior a 24 horas.").build();
        }

        try{
            repository.persist(model);
            GerarAtividadesMedicamentoUso gerarAtividadesMedicamentoUso = new GerarAtividadesMedicamentoUso();
            atividadeMedicamentoResidenteRepository.persist(gerarAtividadesMedicamentoUso.gerar(model));
        }catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status( Response.Status.CREATED.getStatusCode()).entity(model.getId()).build();
    }

    public Response update(long id, MedicamentoUsoDTO medicamentoUsoDTO){
        Set<ConstraintViolation<MedicamentoUsoDTO>> violations = validator.validate(medicamentoUsoDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        MedicamentoUsoModel model = repository.findById(id);
        if(model != null){
            model.setIntervalo(medicamentoUsoDTO.getIntervalo());
            model.setQtdeVezesAoDia(medicamentoUsoDTO.getQtdeVezesAoDia());
            model.setDataHoraInicio(medicamentoUsoDTO.getDataHoraInicio());
            model.setQtdeDiasUso(medicamentoUsoDTO.getQtdeDiasUso());
            model.setQtdeMedicamento(medicamentoUsoDTO.getQtdeMedicamento());

            ResidenteModel residenteModel;
            Optional<ResidenteModel> optional = residenteRepository.findByIdOptional(medicamentoUsoDTO.getResidente());
            if(optional.isPresent()){
                residenteModel = optional.get();
            }else{
                return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Residente não encontrado(a).").build();
            }
            model.setResidente(residenteModel);

            MedicamentoEstoqueModel medicamentoEstoqueModel;
            Optional<MedicamentoEstoqueModel> optionalMedicamentoEstoque = medicamentoEstoqueRepository.findByIdOptional(medicamentoUsoDTO.getMedicamento());
            if(optional.isPresent()){
                medicamentoEstoqueModel = optionalMedicamentoEstoque.get();
            }else{
                return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Medicamento não encontrado(a).").build();
            }
            model.setMedicamento(medicamentoEstoqueModel);

            try{
                repository.persist(model);
            }catch (Exception ex){
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }

            return Response.status(201).entity(model.getId()).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response delete(long id){
        MedicamentoUsoModel model = repository.findById(id);
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
        List<MedicamentoUsoModel> lista = repository.pageList(page,size);
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        PageDTO<MedicamentoUsoModel> pageDTO = new PageDTO<>();
        pageDTO.setLista(lista);
        pageDTO.setPages(repository.pageCount(page,size));
        pageDTO.setTotal(repository.pageTotal(page,size));
        return Response.ok(pageDTO).build();
    }

    public Response pageSort(int page, int size, String atributo, boolean asc){
        if(page < 0 || size < 1){
            return Response.status(422).build();
        }
        List<MedicamentoUsoModel> lista = repository.pageListSort(page,size,atributo,asc ? Sort.Direction.Ascending : Sort.Direction.Descending);
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        PageDTO<MedicamentoUsoModel> pageDTO = new PageDTO<>();
        pageDTO.setLista(lista);
        pageDTO.setPages(repository.pageCount(page,size));
        pageDTO.setTotal(repository.pageTotal(page,size));
        return Response.ok(pageDTO).build();
    }

}
