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
import { PipesModule } from "src/app/shared/pipesmodule/pipesmodule.module";
import { ProfissionalCadastrarComponent } from "./profissional-cadastrar/profissional-cadastrar.component";
import { ProfissionalListarComponent } from "./profissional-listar/profissional-listar.component";
import { InputMaskModule } from "primeng/inputmask";
import { ProfissionalRoutingModule } from "./profissional-routing.module";

@NgModule({
    declarations: [
        ProfissionalCadastrarComponent,
        ProfissionalListarComponent,
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
        InputMaskModule,
        ProfissionalRoutingModule,
    ]
})
export class ProfissionalModule {}