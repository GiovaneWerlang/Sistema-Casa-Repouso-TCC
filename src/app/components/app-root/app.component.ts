import { Component, OnDestroy, OnInit } from '@angular/core';
import { BreakpointserviceService } from './services/breakpointservice.service';
import { BreakpointState, Breakpoints } from '@angular/cdk/layout';
import { PrimeNGConfig } from 'primeng/api';
import { Translate } from 'src/app/shared/translate/translate';
import { AutenticacaoService } from '../login/service/autenticacao.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'client';
  desktop: boolean = true;
  autenticado: boolean = false;

  breakPointSubscription: Subscription = new Subscription;
  autenticacaoSubscription: Subscription = new Subscription;

  constructor(
    private config: PrimeNGConfig,
    breakpointService: BreakpointserviceService,
    private _autenticacaoService: AutenticacaoService) {
    config.setTranslation(Translate);
    this.monitoraBreakspoints(breakpointService);
    this.monitoraAutenticado();
  }

  ngOnInit(): void {
    this._autenticacaoService.autoLogin();
  }

  monitoraBreakspoints(breakpointService: BreakpointserviceService) {
    this.breakPointSubscription = breakpointService.getBreakpoints().subscribe((breakpoint: BreakpointState) => {
      const breakpoints = breakpoint.breakpoints;
      this.desktop = breakpoints[Breakpoints.Web] || breakpoints[Breakpoints.WebLandscape];
    });
  }

  monitoraAutenticado() {
    this.autenticacaoSubscription = this._autenticacaoService.dadoUsuario.asObservable().subscribe(
      dado => {
        this.autenticado = !!dado;
      }
    );
  }

  ngOnDestroy(): void {
    this.breakPointSubscription.unsubscribe();
    this.autenticacaoSubscription.unsubscribe();
  }
}
