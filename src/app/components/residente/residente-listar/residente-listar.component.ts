import { Component } from '@angular/core';
import { ResidenteService } from '../service/residente.service';
import { CrudService } from 'src/app/shared/crud/crud-service/crud-service';
import { Residente } from '../modelo/residente';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { TipoEstadia } from 'src/app/shared/tipoestadia/tipoestadia';
import { Situacoes } from 'src/app/shared/situacoes/situacoes';
import { ConfirmationService } from 'primeng/api';
import { CrudLista } from 'src/app/shared/crud/crud-lista/crud-lista';

@Component({
  selector: 'app-residente-listar',
  templateUrl: './residente-listar.component.html',
  styleUrls: ['./residente-listar.component.css'],
  providers: [{ provide: CrudService, useExisting: ResidenteService }, ConfirmationService]
})
export class ResidenteListarComponent extends CrudLista<Residente> {

  situacoes: LabelValue[] = Situacoes;
  tiposEstadia: LabelValue[] = TipoEstadia;

  ngOnInit(): void {
  }

}
