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
import { AtividadeexameEditarComponent } from "./atividadeexame-editar/atividadeexame-editar.component";
import { AtividadeexameListarComponent } from "./atividadeexame-listar/atividadeexame-listar.component";
import { PipesModule } from "src/app/shared/pipesmodule/pipesmodule.module";
import { AtividadeExameRoutingModule } from "./atividadeexame-routing.module";

@NgModule({
    declarations: [
        AtividadeexameEditarComponent,
        AtividadeexameListarComponent,
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
        AtividadeExameRoutingModule,
    ]
})
export class AtividadeExameModule {}