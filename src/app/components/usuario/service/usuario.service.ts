import { Injectable } from '@angular/core';
import { Usuario } from '../modelo/usuario';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { CrudService } from 'src/app/shared/crud-service/crud-service';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from "src/environments/environment.development";

@Injectable({
  providedIn: 'root'
})
export class UsuarioService extends CrudService<Usuario> {

  constructor(http: HttpClient) {
    super(http, 'usuario');
  }

  login(usuario: any): Observable<any> {
    return this.http.post(`${environment.apiUrl}usuario/login`, usuario, { responseType: 'text' }).pipe(
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
