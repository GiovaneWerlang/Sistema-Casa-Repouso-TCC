import { DashboardService } from './service/dashboard.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers: [DashboardService]
})
export class DashboardComponent implements OnInit {
  data: any;

  options: any;

  constructor(private _dashboardService:DashboardService) {
    this.options = {
      cutout: '50%'
    };
  }

  ngOnInit(): void {
    this._dashboardService.list().subscribe(dados => {
      this.data = dados;      
    });
  }
}
