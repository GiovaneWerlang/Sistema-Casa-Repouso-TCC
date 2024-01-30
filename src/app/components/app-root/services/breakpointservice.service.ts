import { BreakpointObserver, BreakpointState, Breakpoints } from '@angular/cdk/layout';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class BreakpointserviceService {

    breakpointsObserver: Observable<BreakpointState>;

    constructor(breakpoints: BreakpointObserver) {
        this.breakpointsObserver = breakpoints.observe(
            [
                Breakpoints.Web,
                Breakpoints.WebLandscape,
                Breakpoints.WebPortrait,
                Breakpoints.Tablet,
                Breakpoints.TabletPortrait,
                Breakpoints.TabletLandscape,
                Breakpoints.Handset,
                Breakpoints.HandsetPortrait,
                Breakpoints.HandsetLandscape,
                Breakpoints.XSmall,
                Breakpoints.Small,
                Breakpoints.Medium,
                Breakpoints.Large,
                Breakpoints.XLarge
            ]
        );
    }

    getBreakpoints() {
        return this.breakpointsObserver;
    }
}
