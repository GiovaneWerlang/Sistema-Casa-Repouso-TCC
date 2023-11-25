import { MedicamentoUso } from "src/app/components/medicamentouso/modelo/medicamentouso";
import { Profissional } from "src/app/components/profissional/modelo/profissional";

export interface AtividadeMedicamento {
    id?: number;
    descricao: string;
    dataHora: Date;
    situacao: string;
    profissional: Profissional;
    medicamento: MedicamentoUso;
}