import { Injectable } from '@angular/core';
import { MovimentacaoEstoque } from '../modelo/movimentacaoestoque';
import { HttpClient } from '@angular/common/http';
import { CrudService } from 'src/app/shared/crud/crud-service/crud-service';

@Injectable({
  providedIn: 'root'
})
export class MovimentacaoestoqueService extends CrudService<MovimentacaoEstoque> {

  constructor(http:HttpClient) {
    super(http, 'movimentacaoestoque');
  }
}
