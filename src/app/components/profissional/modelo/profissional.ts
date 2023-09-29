import { Especialidade } from "../../especialidade/modelo/especialidade";
import { Endereco } from "./endereco";

export interface Profissional {
    id?:number;
    idade:number;
    cpf:string;
    telefone:string;
    email:string;
    dataAdmissao:Date;
    salario:number;
    situacao:string;
    funcao:string;
    especialidade:Especialidade;
    endereco:Endereco;
}