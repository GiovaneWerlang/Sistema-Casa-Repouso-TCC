import { Injectable } from '@angular/core';
import { Residente } from '../modelo/residente';
import { CrudService } from 'src/app/shared/crud-service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ResidenteService extends CrudService<Residente> {

  constructor(http: HttpClient) {
    super(http, 'residente');
  }
}
