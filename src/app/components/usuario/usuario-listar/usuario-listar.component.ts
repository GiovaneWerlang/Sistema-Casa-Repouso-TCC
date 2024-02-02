import { Component } from '@angular/core';
import { UsuarioService } from '../service/usuario.service';
import { CrudService } from 'src/app/shared/crud/crud-service/crud-service';
import { Usuario } from '../modelo/usuario';
import { Situacoes } from 'src/app/shared/situacoes/situacoes';
import { Funcoes } from 'src/app/shared/funcoes/funcoes';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { ConfirmationService } from 'primeng/api';
import { CrudLista } from 'src/app/shared/crud/crud-lista/crud-lista';

@Component({
  selector: 'app-usuario-listar',
  templateUrl: './usuario-listar.component.html',
  styleUrls: ['./usuario-listar.component.css'],
  providers: [{ provide: CrudService, useExisting: UsuarioService }, ConfirmationService]
})
export class UsuarioListarComponent extends CrudLista<Usuario> {
  
  situacoes: LabelValue[] = Situacoes;
  funcoes: LabelValue[] = Funcoes;

  ngOnInit(): void {
  }
 
}
