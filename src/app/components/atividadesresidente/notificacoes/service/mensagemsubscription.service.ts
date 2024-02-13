import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, catchError, throwError } from "rxjs";
import { environment } from "src/environments/environment.development";
import { MensagemSubscription } from "../modelo/mensagemSubscription";

@Injectable({
    providedIn: 'root'
})
export class MensagemSubscriptionService {

    private API_URL = environment.apiUrl;
    private T_URL = 'mensagemsubscription';

    constructor(protected http: HttpClient) {
    }

    getUrl() {
        return this.T_URL;
    }

    getApiUrl() {
        return this.API_URL;
    }

    subscribe(mensagemSubscription:MensagemSubscription): Observable<any> {
        return this.http.post(`${this.API_URL}${this.T_URL}/subscribe`, mensagemSubscription).pipe(
            catchError(this.handleError)
        );
    }

    unsubscribe(mensagemSubscription:MensagemSubscription): Observable<any> {
        return this.http.post(`${this.API_URL}${this.T_URL}/unsubscribe`, mensagemSubscription).pipe(
            catchError(this.handleError)
        );
    }

    private handleError(error: HttpErrorResponse) {
        return throwError(() => new Error(`${error.status} - ${error.status === 0 ? 'Sem conexão' : error.statusText} `));
    }

}
