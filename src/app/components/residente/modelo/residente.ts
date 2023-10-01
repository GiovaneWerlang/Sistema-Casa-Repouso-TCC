import { Endereco } from "../../profissional/modelo/endereco";

export interface Residente {
    id?:number;
    nome:string;
    idade:number;
    cpf:string;
    telefone:string;
    email:string;
    situacao:string;
    tipoEstadia:string;
    dataHoraIngresso:Date;
    dataHoraPrevisaoSaida?:Date;
    endereco:Endereco;
}