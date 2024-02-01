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
import { ConsultaCadastrarComponent } from "./consulta-cadastrar/consulta-cadastrar.component";
import { ConsultaListarComponent } from "./consulta-listar/consulta-listar.component";
import { PipesModule } from "src/app/shared/pipesmodule/pipesmodule.module";
import { InputTextareaModule } from "primeng/inputtextarea";
import { ConsultaRoutingModule } from "./consulta-routing.module";

@NgModule({
    declarations: [
        ConsultaCadastrarComponent,
        ConsultaListarComponent,
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
        InputTextareaModule,
        ConsultaRoutingModule,
    ]
})
export class ConsultaModule {}