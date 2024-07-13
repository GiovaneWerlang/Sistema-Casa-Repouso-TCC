package br.edu.utfpr.medicamentouso;

import br.edu.utfpr.atividadesresidente.atividademedicamentoresidente.AtividadeMedicamentoResidenteRepository;
import br.edu.utfpr.crud.CrudService;
import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.medicamentoestoque.MedicamentoEstoqueModel;
import br.edu.utfpr.medicamentoestoque.MedicamentoEstoqueRepository;
import br.edu.utfpr.residente.ResidenteModel;
import br.edu.utfpr.residente.ResidenteRepository;
import br.edu.utfpr.utils.ResponseUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@ApplicationScoped
public class MedicamentoUsoService extends CrudService<MedicamentoUsoModel, MedicamentoUsoDTO, MedicamentoUsoRepository> {

    private static final String MENSAGEMRESIDENTE = "Residente não encontrado(a).";
    private static final String MENSAGEMMEDICAMENTO = "Medicamento não encontrado(a).";
    private static final String MENSAGEMINCONSISTENCIA = "Inconsistência nos dados informados.";
    private static final String MENSAGEMCAMPOS = "O campo vezes ao dia não pode ter valor maior que 1 se o intervalo for igual ou maior a 24 horas.";
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
        super(repository, validator);
        this.repository = repository;
        this.residenteRepository = residenteRepository;
        this.medicamentoEstoqueRepository = medicamentoEstoqueRepository;
        this.atividadeMedicamentoResidenteRepository = atividadeMedicamentoResidenteRepository;
        this.validator = validator;
    }

    public Response getAll(){
        List<MedicamentoUsoModel> lista = repository.listAll();
        if(lista.isEmpty()){
            return ResponseUtils.notFound();
        }
        return ResponseUtils.okListaModel(lista);
    }

    public Response findById(long id){
        MedicamentoUsoModel model = repository.findById(id);
        if(model != null){
            return ResponseUtils.okModel(model);
        }

        return ResponseUtils.notFound();
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
            return ResponseUtils.notFoundComMotivo(MENSAGEMRESIDENTE);
        }
        model.setResidente(residenteModel);

        MedicamentoEstoqueModel medicamentoEstoqueModel;
        Optional<MedicamentoEstoqueModel> optionalMedicamentoEstoque = medicamentoEstoqueRepository.findByIdOptional(medicamentoUsoDTO.getMedicamento());
        if(optional.isPresent()){
            medicamentoEstoqueModel = optionalMedicamentoEstoque.get();
        }else{
            return ResponseUtils.notFoundComMotivo(MENSAGEMMEDICAMENTO);
        }
        model.setMedicamento(medicamentoEstoqueModel);

        if(model.getIntervalo() < 24 && model.getQtdeVezesAoDia() * model.getIntervalo() > 24 || model.getIntervalo() < 1){
            return ResponseUtils.inconsistenciaComMotivo(MENSAGEMINCONSISTENCIA);
        }
        if(model.getIntervalo() >= 24 && model.getQtdeVezesAoDia() > 1){
            return  ResponseUtils.inconsistenciaComMotivo(MENSAGEMCAMPOS);
        }

        try{
            repository.persist(model);
            GerarAtividadesMedicamentoUso gerarAtividadesMedicamentoUso = new GerarAtividadesMedicamentoUso();
            atividadeMedicamentoResidenteRepository.persist(gerarAtividadesMedicamentoUso.gerar(model));
        }catch (Exception ex){
            return ResponseUtils.serverError();
        }

        return ResponseUtils.criado(model.getId());
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
                return ResponseUtils.notFoundComMotivo(MENSAGEMRESIDENTE);
            }
            model.setResidente(residenteModel);

            MedicamentoEstoqueModel medicamentoEstoqueModel;
            Optional<MedicamentoEstoqueModel> optionalMedicamentoEstoque = medicamentoEstoqueRepository.findByIdOptional(medicamentoUsoDTO.getMedicamento());
            if(optional.isPresent()){
                medicamentoEstoqueModel = optionalMedicamentoEstoque.get();
            }else{
                return ResponseUtils.notFoundComMotivo(MENSAGEMMEDICAMENTO);
            }
            model.setMedicamento(medicamentoEstoqueModel);

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
        MedicamentoUsoModel model = repository.findById(id);
        if(model != null){
            repository.delete(model);
            return ResponseUtils.okModel(model);
        }

        return ResponseUtils.notFound();
    }

}
