import { Component } from '@angular/core';
import { ConfirmationService } from 'primeng/api';
import { CrudService } from 'src/app/shared/crud/crud-service/crud-service';
import { AtividadeMedicamento } from '../modelo/atividademedicamento';
import { AtividadeMedicamentoService } from '../service/atividademedicamento.service';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { SituacaoAtividade } from 'src/app/shared/situacaoatividade/situacaoatividade';
import { CrudLista } from 'src/app/shared/crud/crud-lista/crud-lista';

@Component({
  selector: 'app-atividademedicamento-listar',
  templateUrl: './atividademedicamento-listar.component.html',
  styleUrls: ['./atividademedicamento-listar.component.css'],
  providers: [{ provide: CrudService, useExisting: AtividadeMedicamentoService }, ConfirmationService]
})
export class AtividademedicamentoListarComponent extends CrudLista<AtividadeMedicamento>{
  situacoes: LabelValue[] = SituacaoAtividade;

  ngOnInit(): void {
  }

}
