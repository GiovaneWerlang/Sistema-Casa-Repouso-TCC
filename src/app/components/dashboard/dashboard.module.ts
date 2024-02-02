import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { DashboardComponent } from "./dashboard.component";
import { TableModule } from "primeng/table";
import { ChartModule } from "primeng/chart";
import { ButtonModule } from "primeng/button";

@NgModule({
    declarations: [
        DashboardComponent
    ],
    imports: [
        CommonModule,
        TableModule,
        ChartModule,
        ButtonModule,
    ],
    exports: [
        DashboardComponent
    ]
})
export class DashboardModule {}