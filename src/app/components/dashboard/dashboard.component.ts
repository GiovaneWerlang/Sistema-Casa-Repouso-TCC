import { AtividadeConsultaService } from '../atividadesresidente/atividadeconsulta/service/atividadeconsulta.service';
import { AtividadeExameService } from '../atividadesresidente/atividadeexame/service/atividadeexame.service';
import { AtividadeMedicamentoService } from '../atividadesresidente/atividademedicamento/service/atividademedicamento.service';
import { DashboardService } from './service/dashboard.service';
import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { AtividadeDashDTO } from './modelo/atividades';
import { GraficoDadoDTO } from './modelo/graficodado';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers: [DashboardService, AtividadeConsultaService, AtividadeExameService, AtividadeMedicamentoService]
})
export class DashboardComponent {
  @Input() atividades:AtividadeDashDTO[] = [];
  @Input() dados: GraficoDadoDTO[] = [];

  options: any;

  constructor(
    private _atividadeConsultaService: AtividadeConsultaService,
    private _atividadeExameService: AtividadeExameService,
    private _atividadeMedicamentoService: AtividadeMedicamentoService,
    private _router: Router,
  ) {
    this.options = {
      cutout: '50%',
      animation: false
    };
  }


  edit(atividade:any){
    if(atividade.tipo === 'Consulta'){
      this._router.navigate([`/${this._atividadeConsultaService.getUrl()}/editar/${atividade?.id}`]);
    } else if(atividade.tipo === 'Exame'){
      this._router.navigate([`/${this._atividadeExameService.getUrl()}/editar/${atividade?.id}`]);
    } else if(atividade.tipo === 'Medicamento'){
      this._router.navigate([`/${this._atividadeMedicamentoService.getUrl()}/editar/${atividade?.id}`]);
    }
  }

  trackByTitle(index:any, item:any){
    return item?.titulo;
  }

  trackById(index:any, item:any){
    return item?.id;
  }
}
