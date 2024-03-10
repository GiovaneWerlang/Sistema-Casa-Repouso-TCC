import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, catchError, throwError } from "rxjs";
import { environment } from "src/environments/environment.development";
import { MensagemSubscription } from "../modelo/mensagemSubscription";

@Injectable({
    providedIn: 'root'
})
export class NotificacoesService {

    private API_URL = environment.apiUrl;
    private T_URL = 'notificacoes';

    constructor(protected http: HttpClient) {
    }

    getUrl() {
        return this.T_URL;
    }

    getApiUrl() {
        return this.API_URL;
    }

    notificarTodos(): Observable<any> {
        return this.http.get(`${this.API_URL}${this.T_URL}/notificar`).pipe(
            catchError(this.handleError)
        );
    }

    notificarUM(subscrition:MensagemSubscription): Observable<any> {
        return this.http.post(`${this.API_URL}${this.T_URL}/notificarum`, subscrition).pipe(
            catchError(this.handleError)
        );
    }

    private handleError(error: HttpErrorResponse) {
        return throwError(() => new Error(`${error.status} - ${error.status === 0 ? 'Sem conexão' : error.statusText} `));
    }

}
