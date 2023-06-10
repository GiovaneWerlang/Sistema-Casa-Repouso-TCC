import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Especialidade } from '../modelo/especialidade';
import { CrudService } from 'src/app/shared/crud-service';

@Injectable({
  providedIn: 'root'
})
export class EspecialidadeService extends CrudService<Especialidade> {

  constructor(http:HttpClient) {
    super(http, 'especialidade');
  }

}
