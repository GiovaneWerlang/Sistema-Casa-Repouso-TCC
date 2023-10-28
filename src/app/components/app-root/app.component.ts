import { Component } from '@angular/core';
import { BreakpointserviceService } from './services/breakpointservice.service';
import { BreakpointState, Breakpoints } from '@angular/cdk/layout';
import { PrimeNGConfig } from 'primeng/api';
import { Translate } from 'src/app/shared/translate/translate';
import { AutenticacaoService } from '../login/service/autenticacao.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'client';
  desktop: boolean = true;
  autenticado: boolean = false;

  constructor(
    private config: PrimeNGConfig,
    breakpointService: BreakpointserviceService,
    private _autenticacaoService:AutenticacaoService) {
    config.setTranslation(Translate);
    this.monitoraBreakspoints(breakpointService);
    this.monitoraAutenticado();
  }

  monitoraBreakspoints(breakpointService: BreakpointserviceService) {
    breakpointService.getBreakpoints().subscribe((breakpoint: BreakpointState) => {
      const breakpoints = breakpoint.breakpoints;
      this.desktop = breakpoints[Breakpoints.Web] || breakpoints[Breakpoints.WebLandscape];
    });
  }

  monitoraAutenticado(){
    this._autenticacaoService.dadoUsuario.asObservable().subscribe(
      dado => {        
        this.autenticado = !!dado;
      }
    );
  }
}
