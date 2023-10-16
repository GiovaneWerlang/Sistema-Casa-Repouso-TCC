import { HttpErrorResponse } from '@angular/common/http';
import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class AutenticacaoService {
  private API_URL = environment.apiUrl;

  constructor(private _http: HttpClient) { }

  login(usuario: any): Observable<any> {
    return this._http.post(`${this.API_URL}autenticacao/login`, usuario, { responseType: 'text' }).pipe(
      catchError(this.handleLoginError)
    );
  }

  private handleLoginError(error: HttpErrorResponse) {    
    if(error?.status === 401){
      return throwError(() => new Error('Usuário ou senha informados estão incorretos.'));
    } else if(error?.status === 404){
      return throwError(() => new Error('Usuário não encontrado.'));
    } else{
      return throwError(() => new Error('Houve um erro tente novamente.'));
    }
  }
  
}
