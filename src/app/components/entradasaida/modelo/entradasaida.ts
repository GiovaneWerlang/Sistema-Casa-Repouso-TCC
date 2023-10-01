import { Residente } from "../../residente/modelo/residente";

export interface EntradaSaida {
    id?:number;
    descricao:string;
    dataHoraSaida:Date;
    dataHoraEntrada:Date;
    residente:Residente;
}