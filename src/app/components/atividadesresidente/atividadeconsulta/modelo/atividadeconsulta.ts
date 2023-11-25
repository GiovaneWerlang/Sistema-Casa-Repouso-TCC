import { Consulta } from "src/app/components/consulta/modelo/consulta";

export interface AtividadeConsulta {
    id?: number;
    descricao: string;
    dataHora: Date;
    situacao: string;
    consulta: Consulta;
}