import { Component } from '@angular/core';
import { ProfissionalService } from '../service/profissional.service';
import { CrudService } from 'src/app/shared/crud/crud-service/crud-service';
import { Profissional } from '../modelo/profissional';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { Situacoes } from 'src/app/shared/situacoes/situacoes';
import { Funcoes } from 'src/app/shared/funcoes/funcoes';
import { ConfirmationService } from 'primeng/api';
import { CrudLista } from 'src/app/shared/crud/crud-lista/crud-lista';

@Component({
  selector: 'app-profissional-listar',
  templateUrl: './profissional-listar.component.html',
  styleUrls: ['./profissional-listar.component.css'],
  providers: [{ provide: CrudService, useExisting: ProfissionalService }, ConfirmationService]
})
export class ProfissionalListarComponent extends CrudLista<Profissional> {


  situacoes: LabelValue[] = Situacoes;
  funcoes: LabelValue[] = Funcoes;

  ngOnInit(): void {
  }

}
