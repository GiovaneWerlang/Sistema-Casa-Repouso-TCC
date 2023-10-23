import { Component } from '@angular/core';
import { Especialidade } from '../modelo/especialidade';
import { CrudTableComponent } from 'src/app/shared/crud-table/crud-table/crud-table.component';
import { CrudService } from 'src/app/shared/crud-service/crud-service';
import { EspecialidadeService } from '../service/especialidade.service';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';

@Component({
  selector: 'app-especialidade-listar',
  templateUrl: './especialidade-listar.component.html',
  styleUrls: ['./especialidade-listar.component.css'],
  providers: [{ provide: CrudService, useExisting: EspecialidadeService }]
})
export class EspecialidadeListarComponent extends CrudTableComponent<Especialidade> {
  override cols: LabelValue[] = [
    { label: "Id", value: "id" }, { label: "Nome", value: "nome" }];

}
