import { MedicamentoEstoque } from "../../medicamentoestoque/modelo/medicamentoestoque";

export interface MovimentacaoEstoque {
    id?:number;
    qtde:number;
    tipo:string;
    medicamento:MedicamentoEstoque;
}