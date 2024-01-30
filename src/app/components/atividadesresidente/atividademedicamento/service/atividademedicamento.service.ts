import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CrudService } from 'src/app/shared/crud/crud-service/crud-service';
import { AtividadeMedicamento } from '../modelo/atividademedicamento';
import { AtividadeResidente } from '../../modelo/atividaderesidente';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AtividadeMedicamentoService extends CrudService<AtividadeMedicamento> {

  constructor(http:HttpClient) {
    super(http, 'atividademedicamento');
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

