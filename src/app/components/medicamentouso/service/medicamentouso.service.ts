import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CrudService } from 'src/app/shared/crud-service/crud-service';
import { MedicamentoUso } from '../modelo/medicamentouso';

@Injectable({
  providedIn: 'root'
})
export class MedicamentousoService extends CrudService<MedicamentoUso> {

  constructor(http:HttpClient) {
    super(http, 'medicamentouso');
  }
}
