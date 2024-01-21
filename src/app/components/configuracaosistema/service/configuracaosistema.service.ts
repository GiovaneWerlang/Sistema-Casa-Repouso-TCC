import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { environment } from 'src/environments/environment.development';
import { Observable, catchError, throwError } from 'rxjs';
import { ConfiguracaoSistema } from '../modelo/configuracaosistema';

@Injectable({
  providedIn: 'root'
})
export class ConfiguracaoSistemaService {

  private API_URL = environment.apiUrl;
  private T_URL = 'configuracaosistema';

  constructor(protected http: HttpClient) {
  }

  getUrl(){
      return this.T_URL;
  }

  getApiUrl(){
      return this.API_URL;
  }
  
  find(): Observable<ConfiguracaoSistema> {
      return this.http.get<ConfiguracaoSistema>(`${this.API_URL}${this.T_URL}`).pipe(
          catchError(this.handleError)
      );
  }

  update(record: ConfiguracaoSistema): Observable<any> {
      return this.http.put(`${this.API_URL}${this.T_URL}/${record['id' as keyof ConfiguracaoSistema]}`, record).pipe(
          catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse) {       
      return throwError(() => new Error(`${error.status} - ${error.status === 0 ? 'Sem conexão' : error.statusText} `));
  }

}
