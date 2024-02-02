import { Component } from '@angular/core';
import { AtividadeLudica } from '../modelo/atividadeludica';
import { AtividadeLudicaService } from '../service/atividadeludica.service';
import { CrudService } from 'src/app/shared/crud/crud-service/crud-service';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { Situacoes } from 'src/app/shared/situacoes/situacoes';
import { ConfirmationService } from 'primeng/api';
import { CrudLista } from 'src/app/shared/crud/crud-lista/crud-lista';

@Component({
  selector: 'app-atividadeludica',
  templateUrl: './atividadeludica-listar.component.html',
  styleUrls: ['./atividadeludica-listar.component.css'],
  providers: [{ provide: CrudService, useExisting: AtividadeLudicaService }, ConfirmationService]
})
export class AtividadeludicaListarComponent extends CrudLista<AtividadeLudica>{

  situacoes: LabelValue[] = Situacoes;

  ngOnInit(): void {
  }

}
