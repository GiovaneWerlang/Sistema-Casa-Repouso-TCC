import { NgModule } from "@angular/core";
import { AtividadeludicaCadastrarComponent } from "./atividadeludica-cadastrar/atividadeludica-cadastrar.component";
import { AtividadeludicaListarComponent } from "./atividadeludica-listar/atividadeludica-listar.component";
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
import { PipesModule } from "src/app/shared/pipesmodule/pipesmodule.module";
import { AtividadeLudicaRoutingModule } from "./atividadeludica-routing.module";

@NgModule({
    declarations: [
        AtividadeludicaCadastrarComponent,
        AtividadeludicaListarComponent,
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
        AtividadeLudicaRoutingModule,
    ]
})
export class AtividadeLudicaModule {}