import { Injectable } from '@angular/core';
import { Consulta } from '../modelo/consulta';
import { HttpClient } from '@angular/common/http';
import { CrudService } from 'src/app/shared/crud/crud-service/crud-service';

@Injectable({
  providedIn: 'root'
})
export class ConsultaService extends CrudService<Consulta> {

  constructor(http:HttpClient) {
    super(http, 'consulta');
  }
}
