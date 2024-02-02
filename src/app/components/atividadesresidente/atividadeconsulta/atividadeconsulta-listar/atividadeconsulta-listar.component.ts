import { Component } from '@angular/core';
import { AtividadeConsulta } from '../modelo/atividadeconsulta';
import { ConfirmationService } from 'primeng/api';
import { CrudService } from 'src/app/shared/crud/crud-service/crud-service';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { SituacaoAtividade } from 'src/app/shared/situacaoatividade/situacaoatividade';
import { AtividadeConsultaService } from '../service/atividadeconsulta.service';
import { CrudLista } from 'src/app/shared/crud/crud-lista/crud-lista';

@Component({
  selector: 'app-atividadeconsulta-listar',
  templateUrl: './atividadeconsulta-listar.component.html',
  styleUrls: ['./atividadeconsulta-listar.component.css'],
  providers: [{ provide: CrudService, useExisting: AtividadeConsultaService }, ConfirmationService]
})
export class AtividadeconsultaListarComponent extends CrudLista<AtividadeConsulta>{

  situacoes: LabelValue[] = SituacaoAtividade;

  ngOnInit(): void {
  }

}