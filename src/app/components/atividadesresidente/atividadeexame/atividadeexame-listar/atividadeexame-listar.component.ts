import { Component } from '@angular/core';
import { ConfirmationService } from 'primeng/api';
import { CrudService } from 'src/app/shared/crud/crud-service/crud-service';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { SituacaoAtividade } from 'src/app/shared/situacaoatividade/situacaoatividade';
import { AtividadeExame } from '../modelo/atividadeexame';
import { AtividadeExameService } from '../service/atividadeexame.service';
import { CrudLista } from 'src/app/shared/crud/crud-lista/crud-lista';

@Component({
  selector: 'app-atividadeexame-listar',
  templateUrl: './atividadeexame-listar.component.html',
  styleUrls: ['./atividadeexame-listar.component.css'],
  providers: [{ provide: CrudService, useExisting: AtividadeExameService }, ConfirmationService]
})
export class AtividadeexameListarComponent extends CrudLista<AtividadeExame>{

  situacoes: LabelValue[] = SituacaoAtividade;

  ngOnInit(): void {
  }

}
