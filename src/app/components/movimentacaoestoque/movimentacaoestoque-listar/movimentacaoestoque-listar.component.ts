import { CrudService } from 'src/app/shared/crud/crud-service/crud-service';
import { MovimentacaoestoqueService } from './../service/movimentacaoestoque.service';
import { Component } from '@angular/core';
import { MovimentacaoEstoque } from '../modelo/movimentacaoestoque';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { TipoMovimentacao } from 'src/app/shared/tipomovimentacao/tipomovimentacao';
import { ConfirmationService } from 'primeng/api';
import { CrudLista } from 'src/app/shared/crud/crud-lista/crud-lista';

@Component({
  selector: 'app-movimentacaoestoque-listar',
  templateUrl: './movimentacaoestoque-listar.component.html',
  styleUrls: ['./movimentacaoestoque-listar.component.css'],
  providers: [{ provide: CrudService, useExisting: MovimentacaoestoqueService }, ConfirmationService]
})
export class MovimentacaoestoqueListarComponent extends CrudLista<MovimentacaoEstoque>{

  tipos: LabelValue[] = TipoMovimentacao;

  ngOnInit(): void {
  }

}
