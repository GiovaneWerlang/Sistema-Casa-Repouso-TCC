import { Component, Input } from '@angular/core';
import { LabelValue } from '../../labelvalue/labelvalue';
import { ConfirmationService } from 'primeng/api';
import { CrudLista } from '../crud-lista/crud-lista';

@Component({
  selector: 'app-crud-table',
  templateUrl: './crud-table.component.html',
  styleUrls: ['./crud-table.component.css'],
  providers: [ConfirmationService]
})
export class CrudTableComponent<T> extends CrudLista<T> {
  cols: LabelValue[] = [];
  @Input() set colunas(colunas: LabelValue[]) {
    this.cols = colunas;
    this.colspan = (colunas.length + 1).toString();
  }

  colspan: string = '1';

  ngOnInit(): void {
  }

}
