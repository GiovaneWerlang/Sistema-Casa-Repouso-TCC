import { Component } from '@angular/core';
import { MedicamentousoService } from '../service/medicamentouso.service';
import { MedicamentoUso } from '../modelo/medicamentouso';
import { CrudService } from 'src/app/shared/crud/crud-service/crud-service';
import { ConfirmationService } from 'primeng/api';
import { CrudLista } from 'src/app/shared/crud/crud-lista/crud-lista';

@Component({
  selector: 'app-medicamentouso-listar',
  templateUrl: './medicamentouso-listar.component.html',
  styleUrls: ['./medicamentouso-listar.component.css'],
  providers: [{ provide: CrudService, useExisting: MedicamentousoService }, ConfirmationService]
})
export class MedicamentousoListarComponent extends CrudLista<MedicamentoUso>{

  ngOnInit(): void {
  }

}
