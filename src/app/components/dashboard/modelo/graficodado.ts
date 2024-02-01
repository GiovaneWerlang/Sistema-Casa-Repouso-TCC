import { Dado } from "./dado";

export interface GraficoDadoDTO {
    titulo:string;
    labels?:string[];
    datasets?:Dado[];
}