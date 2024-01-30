import { Component } from '@angular/core';
import { MedicamentoEstoque } from '../modelo/medicamentoestoque';
import { CrudTableComponent } from 'src/app/shared/crud/crud-table/crud-table.component';
import { CrudService } from 'src/app/shared/crud/crud-service/crud-service';
import { MedicamentoestoqueService } from '../service/medicamentoestoque.service';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { ConfirmationService } from 'primeng/api';

@Component({
  selector: 'app-medicamentoestoque-listar',
  templateUrl: './medicamentoestoque-listar.component.html',
  styleUrls: ['./medicamentoestoque-listar.component.css'],
  providers: [  { provide: CrudService, useExisting: MedicamentoestoqueService }, ConfirmationService]
})
export class MedicamentoestoqueListarComponent extends CrudTableComponent<MedicamentoEstoque> {
  override cols:LabelValue[] = [
    { label:"Id", value:"id"},
    { label:"Nome", value:"nome"},
    { label:"Princípio Ativo", value:"principioAtivo"},
    { label:"Quantidade", value:"qtde"}
  ];
}
