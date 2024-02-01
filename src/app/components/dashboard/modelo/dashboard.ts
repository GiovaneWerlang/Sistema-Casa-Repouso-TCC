import { AtividadeDashDTO } from "./atividades";
import { GraficoDadoDTO } from "./graficodado";

export interface DashboardDTO {
    atividades?:AtividadeDashDTO[];
    dados?:GraficoDadoDTO[];
}