import { Exame } from "src/app/components/exame/modelo/exame";

export interface AtividadeExame {
    id?: number;
    descricao: string;
    dataHora: Date;
    situacao: string;
    exame: Exame;
}