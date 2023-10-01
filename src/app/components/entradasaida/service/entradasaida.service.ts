import { Injectable } from '@angular/core';
import { EntradaSaida } from '../modelo/entradasaida';
import { CrudService } from 'src/app/shared/crud-service/crud-service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EntradasaidaService extends CrudService<EntradaSaida> {

  constructor(http: HttpClient) {
    super(http, 'entradasaida');
  }
}
