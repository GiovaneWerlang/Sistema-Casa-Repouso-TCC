import { Component } from '@angular/core';
import { ConsultaService } from '../service/consulta.service';
import { CrudService } from 'src/app/shared/crud/crud-service/crud-service';
import { Consulta } from '../modelo/consulta';
import { ConfirmationService } from 'primeng/api';
import { CrudLista } from 'src/app/shared/crud/crud-lista/crud-lista';

@Component({
  selector: 'app-consulta-listar',
  templateUrl: './consulta-listar.component.html',
  styleUrls: ['./consulta-listar.component.css'],
  providers: [{ provide: CrudService, useExisting: ConsultaService }, ConfirmationService]
})
export class ConsultaListarComponent extends CrudLista<Consulta>{

  ngOnInit(): void {
  }

}
