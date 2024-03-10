import { AutenticacaoService } from './../login/service/autenticacao.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BreakpointserviceService } from '../app-root/services/breakpointservice.service';
import { BreakpointState, Breakpoints } from '@angular/cdk/layout';
import { Subscription } from 'rxjs';
import { ConfirmationService } from "primeng/api";
import { DadosnotificacoesService } from '../app-root/services/dadosnotificacoes.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnDestroy {

  desktop: boolean = true;
  estaAutenticado = false;
  private usuarioSub: Subscription;
  private breakpointSubscription: Subscription = new Subscription;

  items = [
    {
      label: 'Receber notificações',
      icon: 'pi pi-message',
      command: () => {
        this.confirmarNotificacoes();
      }
    },
    {
      label: 'Logout',
      icon: 'pi pi-sign-out',
      command: () => {
        this.logout();
      }
    }
  ]

  constructor(
    private _router: Router,
    private breakpointService: BreakpointserviceService,
    private autenticacaoService: AutenticacaoService,
    private confirmationService: ConfirmationService,
    private dadosnotificacoesService:DadosnotificacoesService
  ) {
    this.monitoraBreakspoints(breakpointService);
    this.usuarioSub = this.autenticacaoService.dadoUsuario.subscribe(usuario => {
      this.estaAutenticado = !!usuario;
    });
  }

  ngOnInit() {
  }

  monitoraBreakspoints(breakpointService: BreakpointserviceService) {
    this.breakpointSubscription = breakpointService.getBreakpoints().subscribe((breakpoint: BreakpointState) => {
      const breakpoints = breakpoint.breakpoints;
      this.desktop = breakpoints[Breakpoints.Web] || breakpoints[Breakpoints.WebLandscape];
    });
  }

  home() {
    this._router.navigate(['/home']);
  }

  logout() {
    this.autenticacaoService.logout();
  }

  confirmarNotificacoes(){
    this.confirmationService.confirm({
      message: 'Deseja receber notificações de atividades?',
      header: 'Confirmação',
      icon: 'pi pi-info-circle',
      acceptButtonStyleClass: "p-button-danger p-button-text",
      rejectButtonStyleClass: "p-button-text p-button-text",
      acceptIcon: "none",
      rejectIcon: "none",
      accept: () => {
        this.criarInscricao();
      },
      reject: () => {
        this.cancelarInscricao();
      }
  });
  }

  criarInscricao(){
    this.dadosnotificacoesService.setReceberNotificacoes(true);
  }

  cancelarInscricao() {
    this.dadosnotificacoesService.setReceberNotificacoes(false);
  }

  ngOnDestroy() {
    this.usuarioSub.unsubscribe();
    this.breakpointSubscription.unsubscribe();
  }

}
