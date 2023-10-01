import { MedicamentoEstoque } from "../../medicamentoestoque/modelo/medicamentoestoque";
import { Residente } from "../../residente/modelo/residente";

export interface MedicamentoUso {
    id?:number;
    intervalo:number;
    qtdeVezesAodia:number;
    dataHoraInicio:Date;
    qtdeDiasUso:number;
    qtdeMedicamento:number;
    residente:Residente;
    medicamento:MedicamentoEstoque;
}