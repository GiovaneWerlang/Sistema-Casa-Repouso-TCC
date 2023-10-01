import { Injectable } from '@angular/core';
import { MedicamentoEstoque } from '../modelo/medicamentoestoque';
import { HttpClient } from '@angular/common/http';
import { CrudService } from 'src/app/shared/crud-service/crud-service';

@Injectable({
  providedIn: 'root'
})
export class MedicamentoestoqueService extends CrudService<MedicamentoEstoque> {

  constructor(http:HttpClient) {
    super(http, 'medicamentoestoque');
  }
}
