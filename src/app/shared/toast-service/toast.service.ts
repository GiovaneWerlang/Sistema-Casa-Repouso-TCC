import { Injectable } from "@angular/core";
import { MessageService } from "primeng/api";

@Injectable({
    providedIn: 'root'
})
export class ToastService {
    messageService: MessageService;

    constructor(messageService: MessageService) {
        this.messageService = messageService;
    }

    toastSuccess(detalhe: string){
        this.messageService.add({ severity: 'success', summary: 'Sucesso!', detail: detalhe });
    }

    toastWarning(sumario:string, detalhe: string){
       this.messageService.add({ severity: 'warn', summary: sumario, detail: detalhe });
    }

    toastBase(severidade:string, sumario:string, detalhe:string){
        this.messageService.add({ severity: severidade, summary: sumario, detail: detalhe });
    }

}

