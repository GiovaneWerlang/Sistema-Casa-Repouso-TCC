export interface ConfiguracaoSistema {
    id?:number;
    habilitarEnvioEmail:boolean;
    emailLogin?:string;
    emailSenha?:string;
    habilitarEnvioWhats:boolean;
    whatsNumeroId?:string;
    whatsToken?:string;
}