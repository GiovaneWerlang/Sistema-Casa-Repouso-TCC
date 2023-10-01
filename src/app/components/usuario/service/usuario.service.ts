import { Injectable } from '@angular/core';
import { Usuario } from '../modelo/usuario';
import { HttpClient } from '@angular/common/http';
import { CrudService } from 'src/app/shared/crud-service/crud-service';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService extends CrudService<Usuario> {

  constructor(http: HttpClient) {
    super(http, 'usuario');
  }
}
