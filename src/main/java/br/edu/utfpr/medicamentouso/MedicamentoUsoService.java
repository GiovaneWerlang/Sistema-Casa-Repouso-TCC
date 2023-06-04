package br.edu.utfpr.medicamentouso;

import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.medicamentoestoque.MedicamentoEstoqueModel;
import br.edu.utfpr.medicamentoestoque.MedicamentoEstoqueRepository;
import br.edu.utfpr.residente.ResidenteModel;
import br.edu.utfpr.residente.ResidenteRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@ApplicationScoped
public class MedicamentoUsoService {

    private MedicamentoUsoRepository repository;
    private ResidenteRepository residenteRepository;
    private MedicamentoEstoqueRepository medicamentoEstoqueRepository;
    private Validator validator;

    @Inject
    public MedicamentoUsoService(MedicamentoUsoRepository repository, ResidenteRepository residenteRepository, MedicamentoEstoqueRepository medicamentoEstoqueRepository, Validator validator) {
        this.repository = repository;
        this.residenteRepository = residenteRepository;
        this.medicamentoEstoqueRepository = medicamentoEstoqueRepository;
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

        try{
            repository.persist(model);
        }catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status( Response.Status.CREATED.getStatusCode(),model.toString()).build();
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

            return Response.status(201, model.toString()).build();
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

}
