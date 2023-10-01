import { Component } from '@angular/core';
import { MedicamentoEstoque } from '../modelo/medicamentoestoque';
import { CrudTableComponent } from 'src/app/shared/crud-table/crud-table/crud-table.component';
import { CrudService } from 'src/app/shared/crud-service/crud-service';
import { MedicamentoestoqueService } from '../service/medicamentoestoque.service';

@Component({
  selector: 'app-medicamentoestoque-listar',
  templateUrl: './medicamentoestoque-listar.component.html',
  styleUrls: ['./medicamentoestoque-listar.component.css'],
  providers: [  { provide: CrudService, useExisting: MedicamentoestoqueService }]
})
export class MedicamentoestoqueListarComponent extends CrudTableComponent<MedicamentoEstoque> {
  override cols:string[] = ["Id", "Nome", "Princípio Ativo", "Quantidade"];
}
