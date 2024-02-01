import { NgModule } from "@angular/core";
import { ConfirmDialogModule } from "primeng/confirmdialog";
import { ButtonModule } from "primeng/button";
import { CalendarModule } from "primeng/calendar";
import { ReactiveFormsModule } from "@angular/forms";
import { DropdownModule } from "primeng/dropdown";
import { TableModule } from "primeng/table";
import { PaginatorModule } from "primeng/paginator";
import { ProgressSpinnerModule } from "primeng/progressspinner";
import { BlockUIModule } from "primeng/blockui";
import { CommonModule } from "@angular/common";
import { InputTextModule } from "primeng/inputtext";
import { MedicamentousoCadastrarComponent } from "./medicamentouso-cadastrar/medicamentouso-cadastrar.component";
import { MedicamentousoListarComponent } from "./medicamentouso-listar/medicamentouso-listar.component";
import { MedicamentoUsoRoutingModule } from "./medicamentouso-routing.module";

@NgModule({
    declarations: [
        MedicamentousoCadastrarComponent,
        MedicamentousoListarComponent,
    ],
    imports: [
        CommonModule,
        ReactiveFormsModule,
        ButtonModule,
        ConfirmDialogModule,
        ProgressSpinnerModule,
        BlockUIModule,
        TableModule,
        PaginatorModule,
        InputTextModule,
        DropdownModule,
        CalendarModule,
        MedicamentoUsoRoutingModule,
    ]
})
export class MedicamentoUsoModule {}