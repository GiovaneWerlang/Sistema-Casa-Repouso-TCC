import { Injectable } from '@angular/core';
import { DashboardDTO } from '../modelo/dashboard';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap } from 'rxjs';
import { environment } from 'src/environments/environment.development';

@Injectable()
export class DashboardService {

    private API_URL = environment.apiUrl;
    T_URL = 'dashboard'

    constructor(protected http: HttpClient) {
   }

    list() {
        return this.http.get<DashboardDTO[]>(`${this.API_URL}${this.T_URL}`)
    }
}
