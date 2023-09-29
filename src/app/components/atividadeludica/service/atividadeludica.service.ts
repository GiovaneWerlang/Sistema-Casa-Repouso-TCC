import { Injectable } from '@angular/core';
import { CrudService } from 'src/app/shared/crud-service';
import { AtividadeLudica } from '../modelo/atividadeludica';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AtividadeLudicaService extends CrudService<AtividadeLudica> {

  constructor(http: HttpClient) {
    super(http, 'atividadeludica');
  }
}
