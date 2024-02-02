import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { HomeComponent } from "./home.component";
import { HomeRoutingModule } from "./home-routing.module";
import { DashboardModule } from "../dashboard/dashboard.module";

@NgModule({
    declarations: [
        HomeComponent
    ],
    imports: [
        CommonModule,
        DashboardModule,
        HomeRoutingModule
    ]
})
export class HomeModule {}