import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { BreakpointserviceService } from '../app-root/services/breakpointservice.service';
import { BreakpointState, Breakpoints } from '@angular/cdk/layout';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  desktop: boolean = true;

  constructor(private _router: Router, private breakpointService: BreakpointserviceService) {
    this.monitoraBreakspoints(breakpointService);
  }

  monitoraBreakspoints(breakpointService: BreakpointserviceService) {
    breakpointService.getBreakpoints().subscribe((breakpoint: BreakpointState) => {
      const breakpoints = breakpoint.breakpoints;
      this.desktop = breakpoints[Breakpoints.Web] || breakpoints[Breakpoints.WebLandscape];      
    });
  }

  home(){
    this._router.navigate(['/home']);
  }
}
