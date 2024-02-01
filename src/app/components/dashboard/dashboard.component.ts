import { Subscription } from 'rxjs';
import { AtividadeConsultaService } from '../atividadesresidente/atividadeconsulta/service/atividadeconsulta.service';
import { AtividadeExameService } from '../atividadesresidente/atividadeexame/service/atividadeexame.service';
import { AtividadeMedicamentoService } from '../atividadesresidente/atividademedicamento/service/atividademedicamento.service';
import { DashboardService } from './service/dashboard.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AtividadeDashDTO } from './modelo/atividades';
import { GraficoDadoDTO } from './modelo/graficodado';
import { DashboardDTO } from './modelo/dashboard';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers: [DashboardService, AtividadeConsultaService, AtividadeExameService, AtividadeMedicamentoService]
})
export class DashboardComponent implements OnInit, OnDestroy {
  data: DashboardDTO | undefined;
  atividades:AtividadeDashDTO[] = [];
  dados: GraficoDadoDTO[] = [];

  options: any;
  carregando: boolean = false;

  dashBoardSubscription: Subscription = new Subscription;

  constructor(private _dashboardService: DashboardService,
    private _atividadeConsultaService: AtividadeConsultaService,
    private _atividadeExameService: AtividadeExameService,
    private _atividadeMedicamentoService: AtividadeMedicamentoService,
    private _router: Router,
  ) {
    this.options = {
      cutout: '50%',
      animation: false
    };
    this.carregando = true;
  }

  ngOnInit(): void {
    this.carregarDadosDashboard();
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

  carregarDadosDashboard() {
    this.dashBoardSubscription = this._dashboardService.list().subscribe((dados:any) => {
      this.atividades = dados?.atividades;
      this.dados = dados?.dados;
      this.carregando = false;
    });
  }  

  ngOnDestroy(): void {
    this.dashBoardSubscription.unsubscribe();
  }

}
