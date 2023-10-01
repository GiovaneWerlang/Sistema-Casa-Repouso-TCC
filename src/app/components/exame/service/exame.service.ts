import { Injectable } from '@angular/core';
import { Exame } from '../modelo/exame';
import { HttpClient } from '@angular/common/http';
import { CrudService } from 'src/app/shared/crud-service/crud-service';

@Injectable({
  providedIn: 'root'
})
export class ExameService extends CrudService<Exame> {

  constructor(http:HttpClient) {
    super(http, 'exame');
  }
}
