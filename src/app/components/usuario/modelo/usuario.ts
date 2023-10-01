import { Profissional } from "../../profissional/modelo/profissional";

export interface Usuario{
    id?:number;
    login:string;
    senha:string;
    profissional:Profissional;
}