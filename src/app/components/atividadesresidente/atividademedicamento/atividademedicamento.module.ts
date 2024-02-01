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
import { AtividademedicamentoEditarComponent } from "./atividademedicamento-editar/atividademedicamento-editar.component";
import { AtividademedicamentoListarComponent } from "./atividademedicamento-listar/atividademedicamento-listar.component";
import { PipesModule } from "src/app/shared/pipesmodule/pipesmodule.module";
import { AtividadeMedicamentoRoutingModule } from "./atividademedicamento-routing.module";

@NgModule({
    declarations: [
        AtividademedicamentoEditarComponent,
        AtividademedicamentoListarComponent,
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
        PipesModule,
        AtividadeMedicamentoRoutingModule,
    ]
})
export class AtividadeMedicamentoModule {}