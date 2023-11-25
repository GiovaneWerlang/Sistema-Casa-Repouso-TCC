import { Injectable } from '@angular/core';
import { AtividadeConsulta } from '../modelo/atividadeconsulta';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { CrudService } from 'src/app/shared/crud-service/crud-service';
import { Observable, catchError, throwError } from 'rxjs';
import { AtividadeResidente } from '../../modelo/atividaderesidente';

@Injectable({
  providedIn: 'root'
})
export class AtividadeConsultaService extends CrudService<AtividadeConsulta> {

  constructor(http:HttpClient) {
    super(http, 'atividadeconsulta');
  }

  updateSituacao(id:number, atividadeResidente: AtividadeResidente): Observable<any> {
    return this.http.put(`${super.getApiUrl()}${super.getUrl()}/${id}`, atividadeResidente).pipe(
        catchError(this.handleErrorUpdateSituacao)
    );
  }

  private handleErrorUpdateSituacao(error: HttpErrorResponse) {       
    return throwError(() => new Error(`${error.status} - ${error.status === 0 ? 'Sem conexão' : error.statusText} `));
  }

}
