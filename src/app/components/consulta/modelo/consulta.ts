import { Especialidade } from "../../especialidade/modelo/especialidade";
import { Profissional } from "../../profissional/modelo/profissional";
import { Residente } from "../../residente/modelo/residente";

export interface Consulta {
    id?:number;
    descricao:string;
    dataHora:Date;
    local:string;
    prescricao?:string;
    especialidade:Especialidade;
    profissional:Profissional;
    residente:Residente;
}