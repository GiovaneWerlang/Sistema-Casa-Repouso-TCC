import { AutenticacaoService } from './../login/service/autenticacao.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BreakpointserviceService } from '../app-root/services/breakpointservice.service';
import { BreakpointState, Breakpoints } from '@angular/cdk/layout';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnDestroy {

  desktop: boolean = true;
  estaAutenticado = false;
  private usuarioSub: Subscription;

  items = [
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
    private autenticacaoService: AutenticacaoService
  ) {
    this.monitoraBreakspoints(breakpointService);
    this.usuarioSub = this.autenticacaoService.dadoUsuario.subscribe(usuario => {
      this.estaAutenticado = !!usuario;
    });
    // let elements = document.getElementsByClassName("p-menuitem-link");
    // for (let i = 0; i < elements.length; i++) {
    //     elements[i].ariaLabel = this.items[i].label;
    // }
  }

  ngOnInit() {
  }

  monitoraBreakspoints(breakpointService: BreakpointserviceService) {
    breakpointService.getBreakpoints().subscribe((breakpoint: BreakpointState) => {
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

  ngOnDestroy() {
    this.usuarioSub.unsubscribe();
  }

}
