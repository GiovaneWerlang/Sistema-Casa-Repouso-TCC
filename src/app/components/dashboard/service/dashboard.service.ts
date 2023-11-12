import { Injectable } from '@angular/core';
import { DashboardDTO } from '../modelo/dashboard';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap } from 'rxjs';
import { environment } from 'src/environments/environment.development';

@Injectable()
export class DashboardService {

    private API_URL = environment.apiUrl;
    headers: HttpHeaders;
    T_URL = 'dashboard'

    constructor(protected http: HttpClient) {
        let dados = localStorage.getItem('dadosUsuario');
        let token = '';
        if (dados) {
            token = JSON.parse(dados)._token;
        }
        this.headers = new HttpHeaders().set('Authorization', 'Bearer ' + token);
    }

    list() {
        return this.http.get<DashboardDTO[]>(`${this.API_URL}${this.T_URL}`, { 'headers': this.headers })
    }
}
