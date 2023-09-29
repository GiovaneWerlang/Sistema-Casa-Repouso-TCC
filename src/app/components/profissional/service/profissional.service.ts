import { Injectable } from '@angular/core';
import { Profissional } from '../modelo/profissional';
import { CrudService } from 'src/app/shared/crud-service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProfissionalService extends CrudService<Profissional> {

  constructor(http: HttpClient) {
    super(http, 'profissional');
  }
}
