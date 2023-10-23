package br.edu.utfpr.movimentacaoestoque;

import br.edu.utfpr.crud.CrudService;
import br.edu.utfpr.erro.ResponseError;
import br.edu.utfpr.medicamentoestoque.MedicamentoEstoqueModel;
import br.edu.utfpr.medicamentoestoque.MedicamentoEstoqueRepository;
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
public class MovimentacaoEstoqueService implements CrudService<MovimentacaoEstoqueDTO> {

    private static final String MENSAGEM = "Quantidade insuficiente.";

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
                        return Response.status(Response.Status.NOT_MODIFIED.getStatusCode(), MENSAGEM).build();
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

        return Response.status( Response.Status.CREATED.getStatusCode()).entity(model.getId()).build();
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
                            return Response.status(Response.Status.NOT_MODIFIED.getStatusCode(), MENSAGEM).build();
                        }
                        break;
                    }
                    case SAIDA:{
                        valorAtualizado = (geraValorAtualizar(medicamentoEstoqueModel.getQtde(), qtdeAnterior, model.getQtde(), false));
                        if(valorAtualizado >= 0){
                            medicamentoEstoqueModel.setQtde(valorAtualizado);
                        }else{
                            return Response.status(Response.Status.NOT_MODIFIED.getStatusCode(), MENSAGEM).build();
                        }
                        break;
                    }
                    default:{
                        return Response.status(Response.Status.NOT_MODIFIED.getStatusCode(), "Tipo não encontrado.").build();
                    }
                }
            }
            model.setMedicamento(medicamentoEstoqueModel);

            return Response.status(201).entity(model.getId()).build();
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

    public Response page(int page, int size){
        if(page < 0 || size < 1){
            return Response.status(422).build();
        }
        List<MovimentacaoEstoqueModel> lista = repository.pageList(page,size);
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        PageDTO<MovimentacaoEstoqueModel> pageDTO = new PageDTO<>();
        pageDTO.setLista(lista);
        pageDTO.setPages(repository.pageCount(page,size));
        pageDTO.setTotal(repository.pageTotal(page,size));
        return Response.ok(pageDTO).build();
    }

    public Response pageSort(int page, int size, String atributo, boolean asc){
        if(page < 0 || size < 1){
            return Response.status(422).build();
        }
        List<MovimentacaoEstoqueModel> lista = repository.pageListSort(page,size,atributo,asc ? Sort.Direction.Ascending : Sort.Direction.Descending);
        if(lista.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        PageDTO<MovimentacaoEstoqueModel> pageDTO = new PageDTO<>();
        pageDTO.setLista(lista);
        pageDTO.setPages(repository.pageCount(page,size));
        pageDTO.setTotal(repository.pageTotal(page,size));
        return Response.ok(pageDTO).build();
    }

}
