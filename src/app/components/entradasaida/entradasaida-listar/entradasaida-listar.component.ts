import { Component } from '@angular/core';
import { EntradasaidaService } from '../service/entradasaida.service';
import { CrudService } from 'src/app/shared/crud/crud-service/crud-service';
import { EntradaSaida } from '../modelo/entradasaida';
import { ConfirmationService } from 'primeng/api';
import { CrudLista } from 'src/app/shared/crud/crud-lista/crud-lista';

@Component({
  selector: 'app-entradasaida-listar',
  templateUrl: './entradasaida-listar.component.html',
  styleUrls: ['./entradasaida-listar.component.css'],
  providers: [{ provide: CrudService, useExisting: EntradasaidaService }, ConfirmationService]

})
export class EntradasaidaListarComponent extends CrudLista<EntradaSaida> {
  ngOnInit(): void {
  }

}
