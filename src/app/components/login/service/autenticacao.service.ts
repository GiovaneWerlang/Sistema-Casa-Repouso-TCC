import { HttpErrorResponse } from '@angular/common/http';
import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, catchError, tap, throwError } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { DadoUsuario } from '../modelo/dadousuario.model';
import { Router } from '@angular/router';

export interface DadoResponseAutenticacao {
  id: number,
  nome: string,
  token: string,
  funcao: string,
  dataHoraExpiracao: string
}

@Injectable({
  providedIn: 'root'
})
export class AutenticacaoService {
  private API_URL = environment.apiUrl;
  dadoUsuario = new BehaviorSubject<any>(false);

  constructor(private _http: HttpClient, private router: Router) { }

  observe():Observable<DadoUsuario> {
    return this.dadoUsuario.asObservable()
  }

  login(usuario: any): Observable<DadoResponseAutenticacao> {
    return this._http.post<DadoResponseAutenticacao>(`${this.API_URL}autenticacao/login`, usuario).pipe(
      catchError(this.handleLoginError),
      tap(resData => {
        this.handleAuthentication(
          resData.id,
          resData.nome,
          resData.token,
          resData.funcao,
          resData.dataHoraExpiracao
        );
      })
    );
  }

  logout() {   
    localStorage.removeItem('dadosUsuario');
    this.dadoUsuario.next(false);
    this.router.navigate(['/login']);
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

  private handleAuthentication(
    id: number,
    nome: string,
    token: string,
    funcao: string,
    dataHoraExpiracao: string
  ) {
    const dataExpiracao = new Date(dataHoraExpiracao);
    const dadoUsuario = new DadoUsuario(id, nome, token, funcao, dataExpiracao);    
    this.dadoUsuario.next(dadoUsuario);
    localStorage.setItem('dadosUsuario', JSON.stringify(dadoUsuario));
  }
  
}
