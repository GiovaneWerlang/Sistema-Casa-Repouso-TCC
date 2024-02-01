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
import { EnumPipe } from "src/app/shared/enum-pipe/enum-pipe.pipe";
import { InputTextModule } from "primeng/inputtext";
import { AtividadeconsultaEditarComponent } from "./atividadeconsulta-editar/atividadeconsulta-editar.component";
import { AtividadeconsultaListarComponent } from "./atividadeconsulta-listar/atividadeconsulta-listar.component";
import { PipesModule } from "src/app/shared/pipesmodule/pipesmodule.module";
import { AtividadeConsultaRoutingModule } from "./atividadeconsulta-routing.module";

@NgModule({
    declarations: [
        AtividadeconsultaEditarComponent,
        AtividadeconsultaListarComponent,
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
        AtividadeConsultaRoutingModule,
    ]
})
export class AtividadeConsultaModule {}