import { Subscription } from 'rxjs';
import { AtividadeConsultaService } from '../atividadesresidente/atividadeconsulta/service/atividadeconsulta.service';
import { AtividadeExameService } from '../atividadesresidente/atividadeexame/service/atividadeexame.service';
import { AtividadeMedicamentoService } from '../atividadesresidente/atividademedicamento/service/atividademedicamento.service';
import { DashboardService } from './service/dashboard.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers: [DashboardService, AtividadeConsultaService, AtividadeExameService, AtividadeMedicamentoService]
})
export class DashboardComponent implements OnInit, OnDestroy {
  data: any;

  options: any;
  carregando: boolean = false;
  atividades: any[] = [];
  atividadesConsulta: any[] = [];
  atividadesExame: any[] = [];
  atividadesMedicamento: any[] = [];

  dashBoardSubscription: Subscription = new Subscription;

  atividadesConsultaSubscription: Subscription = new Subscription;
  atividadesExameSubscription: Subscription = new Subscription;
  atividadesMedicamentoSubscription: Subscription = new Subscription;

  constructor(private _dashboardService: DashboardService,
    private _atividadeConsultaService: AtividadeConsultaService,
    private _atividadeExameService: AtividadeExameService,
    private _atividadeMedicamentoService: AtividadeMedicamentoService,
    private _router: Router,
  ) {
    this.options = {
      cutout: '50%'
    };
    this.carregando = true;
  }

  ngOnInit(): void {
    this.carregarDadosDashboard();
    this.carregarAtividadesConsulta();
    this.carregarAtividadesExame();
    this.carregarAtividadesMedicamento();
  }

  edit(atividade:any){
    if(atividade.hasOwnProperty('consulta')){
      this._router.navigate([`/${this._atividadeConsultaService.getUrl()}/editar/${atividade?.id}`]);
    } else if(atividade.hasOwnProperty('exame')){
      this._router.navigate([`/${this._atividadeExameService.getUrl()}/editar/${atividade?.id}`]);
    } else if(atividade.hasOwnProperty('medicamento')){
      this._router.navigate([`/${this._atividadeMedicamentoService.getUrl()}/editar/${atividade?.id}`]);
    }
  }

  carregarDadosDashboard() {
    this.dashBoardSubscription = this._dashboardService.list().subscribe(dados => {
      this.data = dados;
      this.carregando = false;
    });
  }

  carregarAtividadesConsulta() {
    this.atividadesConsultaSubscription = this._atividadeConsultaService.list().subscribe(atividadesConsulta => {
      if (atividadesConsulta)
        this.atividades.push.apply(this.atividades, atividadesConsulta);
    })
  }

  carregarAtividadesExame() {
    this.atividadesExameSubscription = this._atividadeExameService.list().subscribe(atividadesExame => {
      if (atividadesExame)
        this.atividades.push.apply(this.atividades, atividadesExame);
    })
  }

  carregarAtividadesMedicamento() {
    this.atividadesMedicamentoSubscription = this._atividadeMedicamentoService.list().subscribe(atividadesMedicamento => {
      if (atividadesMedicamento)
        this.atividades.push.apply(this.atividades, atividadesMedicamento);
    })
  }

  ngOnDestroy(): void {
    this.dashBoardSubscription.unsubscribe();
    this.atividadesConsultaSubscription.unsubscribe();
    this.atividadesExameSubscription.unsubscribe();
    this.atividadesMedicamentoSubscription.unsubscribe();
  }

}
