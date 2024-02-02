import { Component } from '@angular/core';
import { ExameService } from '../service/exame.service';
import { CrudService } from 'src/app/shared/crud/crud-service/crud-service';
import { Exame } from '../modelo/exame';
import { ConfirmationService } from 'primeng/api';
import { CrudLista } from 'src/app/shared/crud/crud-lista/crud-lista';

@Component({
  selector: 'app-exame-listar',
  templateUrl: './exame-listar.component.html',
  styleUrls: ['./exame-listar.component.css'],
  providers: [{ provide: CrudService, useExisting: ExameService }, ConfirmationService]
})
export class ExameListarComponent extends CrudLista<Exame>{

  ngOnInit(): void {
  }

}
