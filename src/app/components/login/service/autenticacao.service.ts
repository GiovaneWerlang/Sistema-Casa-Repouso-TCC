import { HttpErrorResponse } from '@angular/common/http';
import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, catchError, tap, throwError } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { DadoUsuario } from '../modelo/dadousuario.model';
import { Router } from '@angular/router';
import { jwtDecode } from "jwt-decode";

export interface DadoResponseAutenticacao {
  token: string
}

@Injectable({
  providedIn: 'root'
})
export class AutenticacaoService {
  private API_URL = environment.apiUrl;
  dadoUsuario = new BehaviorSubject<DadoUsuario | null>(null);

  constructor(private _http: HttpClient, private router: Router) { }

  observe():Observable<DadoUsuario | null> {
    return this.dadoUsuario.asObservable()
  }

  login(usuario: any): Observable<DadoResponseAutenticacao> {
    return this._http.post<DadoResponseAutenticacao>(`${this.API_URL}autenticacao/login`, usuario).pipe(
      catchError(this.handleLoginError),
      tap(resData => {
        this.handleAuthentication(
          resData.token
        );
      })
    );
  }

  logout() {
    localStorage.removeItem('dadosUsuario');
    this.dadoUsuario.next(null);
    this.router.navigate(['/login']);
  }

  autoLogin() {
    const dadoUsuario = JSON.parse(localStorage.getItem('dadosUsuario') || '{}');
   
    if (!dadoUsuario) {
      this.logout();
      return;
    }

    if(this.validaTokenExpirado(dadoUsuario.getDataHora)){
      this.logout();
      return;
    }
    const usuario = new DadoUsuario(dadoUsuario.id, dadoUsuario.nome, dadoUsuario._token, dadoUsuario.funcao, new Date(dadoUsuario._dataHoraExpiracao));  

    if (usuario.getToken) {
      this.dadoUsuario.next(usuario);
    }
    
  }

  validaTokenExpirado(dataHora:string){
    const dataExpiracao = new Date(dataHora);    
    return dataExpiracao.getTime() < new Date().getTime();
  }

  private handleLoginError(error: HttpErrorResponse) {    
    console.log(error);
    
    if(error?.status === 401){
      return throwError(() => new Error('Usuário ou senha informados estão incorretos.'));
    } else if(error?.status === 404){
      return throwError(() => new Error('Usuário não encontrado.'));
    } else{
      return throwError(() => new Error('Houve um erro tente novamente.'));
    }
  }

  private handleAuthentication(
    token:string
  ) {
    const decoded:any = jwtDecode(token);    
    const dataExpiracao = new Date(decoded?.exp * 1000);    
    const dadoUsuario = new DadoUsuario(decoded?.sub, decoded?.full_name, token, decoded?.groups[0], dataExpiracao);        
    this.dadoUsuario.next(dadoUsuario);
    localStorage.setItem('dadosUsuario', JSON.stringify(dadoUsuario));
  }
  
}
