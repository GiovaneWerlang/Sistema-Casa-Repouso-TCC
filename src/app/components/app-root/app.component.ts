import { Component } from '@angular/core';
import { BreakpointserviceService } from './services/breakpointservice.service';
import { BreakpointState, Breakpoints } from '@angular/cdk/layout';
import { PrimeNGConfig } from 'primeng/api';
import { Translate } from 'src/app/shared/translate/translate';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'client';
  desktop: boolean = true;

  constructor(
    private config: PrimeNGConfig,
    breakpointService: BreakpointserviceService) {
    config.setTranslation(Translate);
    this.monitoraBreakspoints(breakpointService);
  }

  monitoraBreakspoints(breakpointService: BreakpointserviceService) {
    breakpointService.getBreakpoints().subscribe((breakpoint: BreakpointState) => {
      const breakpoints = breakpoint.breakpoints;
      this.desktop = breakpoints[Breakpoints.Web] || breakpoints[Breakpoints.WebLandscape];
    });
  }
}
