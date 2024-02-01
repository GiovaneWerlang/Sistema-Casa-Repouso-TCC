import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { AtividadeDashDTO } from '../dashboard/modelo/atividades';
import { GraficoDadoDTO } from '../dashboard/modelo/graficodado';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  dashBoardSubscription: Subscription = new Subscription;

  atividades:AtividadeDashDTO[] = [];
  dados: GraficoDadoDTO[] = [];

  constructor(private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.carregarDadosDashboard();
  }

  carregarDadosDashboard() {
    this.dashBoardSubscription = this.activatedRoute.data.subscribe((data:any) => {
      this.atividades = data?.resolver?.atividades;
      this.dados = data?.resolver?.dados;
    });
  }  

  ngOnDestroy(): void {
    this.dashBoardSubscription.unsubscribe();
  }
}
