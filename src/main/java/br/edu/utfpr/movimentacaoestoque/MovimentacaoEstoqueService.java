package br.edu.utfpr.movimentacaoestoque;

import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.medicamentoestoque.MedicamentoEstoqueModel;
import br.edu.utfpr.medicamentoestoque.MedicamentoEstoqueRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class MovimentacaoEstoqueService {

    private MovimentacaoEstoqueRepository repository;
    private MedicamentoEstoqueRepository medicamentoEstoqueRepository;
    private Validator validator;

    @Inject

    public MovimentacaoEstoqueService(MovimentacaoEstoqueRepository repository, MedicamentoEstoqueRepository medicamentoEstoqueRepository, Validator validator) {
        this.repository = repository;
        this.medicamentoEstoqueRepository = medicamentoEstoqueRepository;
        this.validator = validator;
    }

    public Response getAll(){
        List<MovimentacaoEstoqueModel> lista = repository.listAll();
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lista).build();
    }

    public Response findById(long id){
        MovimentacaoEstoqueModel model = repository.findById(id);
        if(model != null){
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response add(MovimentacaoEstoqueDTO movimentacaoEstoqueDTO){
        Set<ConstraintViolation<MovimentacaoEstoqueDTO>> violations = validator.validate(movimentacaoEstoqueDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        MovimentacaoEstoqueModel model = new MovimentacaoEstoqueModel();
        model.setQtde(movimentacaoEstoqueDTO.getQtde());
        model.setTipo(movimentacaoEstoqueDTO.getTipo());

        MedicamentoEstoqueModel medicamentoEstoqueModel;
        if(medicamentoEstoqueRepository.findById(movimentacaoEstoqueDTO.getMedicamento()) != null){
            medicamentoEstoqueModel = medicamentoEstoqueRepository.findById(movimentacaoEstoqueDTO.getMedicamento());
        }else{
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Medicamento não encontrado.").build();
        }
        model.setMedicamento(medicamentoEstoqueModel);

        try{
            repository.persist(model);
            switch (model.getTipo()){
                case ENTRADA:{
                    medicamentoEstoqueModel.setQtde(medicamentoEstoqueModel.getQtde() + model.getQtde());
                    break;
                }
                case SAIDA:{
                    if(medicamentoEstoqueModel.getQtde() >= model.getQtde()){
                        medicamentoEstoqueModel.setQtde(medicamentoEstoqueModel.getQtde() - model.getQtde());
                    }else{
                        return Response.status(Response.Status.NOT_MODIFIED.getStatusCode(), "Quantidade insuficiente.").build();
                    }
                    break;
                }
                default:{
                    return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Tipo não encontrado.").build();
                }
            }
        }catch (Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status( Response.Status.CREATED.getStatusCode(),model.toString()).build();
    }

    public Response update(long id, MovimentacaoEstoqueDTO movimentacaoEstoqueDTO){
        Set<ConstraintViolation<MovimentacaoEstoqueDTO>> violations = validator.validate(movimentacaoEstoqueDTO);
        if(!violations.isEmpty()){
            return ResponseError.createFromViolations(violations).returnWithStatusCode(422);
        }

        MovimentacaoEstoqueModel model = repository.findById(id);
        if(model != null){
            Integer qtdeAnterior = model.getQtde();
            model.setQtde(movimentacaoEstoqueDTO.getQtde());
            model.setTipo(movimentacaoEstoqueDTO.getTipo());

            MedicamentoEstoqueModel medicamentoEstoqueModel;
            if(medicamentoEstoqueRepository.findById(movimentacaoEstoqueDTO.getMedicamento()) != null){
                medicamentoEstoqueModel = medicamentoEstoqueRepository.findById(movimentacaoEstoqueDTO.getMedicamento());
            }else{
                return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Medicamento não encontrado.").build();
            }

            if(!qtdeAnterior.equals(movimentacaoEstoqueDTO.getQtde())){
                Integer valorAtualizado = -1;
                switch (model.getTipo()){
                    case ENTRADA:{
                        valorAtualizado = (geraValorAtualizar(medicamentoEstoqueModel.getQtde(), qtdeAnterior, model.getQtde(), true));
                        if(valorAtualizado >= 0) {
                            medicamentoEstoqueModel.setQtde(valorAtualizado);
                        }else{
                            return Response.status(Response.Status.NOT_MODIFIED.getStatusCode(), "Quantidade insuficiente.").build();
                        }
                        break;
                    }
                    case SAIDA:{
                        valorAtualizado = (geraValorAtualizar(medicamentoEstoqueModel.getQtde(), qtdeAnterior, model.getQtde(), false));
                        if(valorAtualizado >= 0){
                            medicamentoEstoqueModel.setQtde(valorAtualizado);
                        }else{
                            return Response.status(Response.Status.NOT_MODIFIED.getStatusCode(), "Quantidade insuficiente.").build();
                        }
                        break;
                    }
                    default:{
                        return Response.status(Response.Status.NOT_MODIFIED.getStatusCode(), "Tipo não encontrado.").build();
                    }
                }
            }
            model.setMedicamento(medicamentoEstoqueModel);

            return Response.status(201, model.toString()).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response delete(long id){
        MovimentacaoEstoqueModel model = repository.findById(id);
        if(model != null){
            repository.delete(model);
            return Response.ok(model).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    private boolean valorAumentou(Integer valorAnterior, Integer valorNovo){
        return valorAnterior < valorNovo;
    }

    private Integer geraValorAtualizar(Integer qtdeEstoque, Integer valorAnterior, Integer valorNovo, boolean entrada){
        Integer valor = -1;
        if(entrada){
            if(valorAumentou(valorAnterior,valorNovo)){
                valor = qtdeEstoque + (valorNovo - valorAnterior);
            }else{
                valor = qtdeEstoque - (valorAnterior - valorNovo);
            }
        }else{
            if(!valorAumentou(valorAnterior,valorNovo)){
                valor = qtdeEstoque + (valorAnterior - valorNovo);
            }else{
                valor = qtdeEstoque - (valorNovo - valorAnterior);
            }
        }
        return valor;
    }

}
