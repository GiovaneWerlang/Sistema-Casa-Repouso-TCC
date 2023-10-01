import { Residente } from './../../residente/modelo/residente';
import { Profissional } from './../../profissional/modelo/profissional';
import { Especialidade } from './../../especialidade/modelo/especialidade';
export interface Exame {
    id?:number;
    nome:string;
    dataHora:Date;
    local:string;
    laudo?:string;
    especialidade:Especialidade;
    profissional:Profissional;
    residente:Residente;
}