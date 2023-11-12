import { Dado } from "./dado";

export interface DashboardDTO {
    titulo:string;
    labels?:string[];
    datasets?:Dado[];
}