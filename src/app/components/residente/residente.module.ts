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
import { InputMaskModule } from "primeng/inputmask";
import { ResidenteCadastrarComponent } from "./residente-cadastrar/residente-cadastrar.component";
import { ResidenteListarComponent } from "./residente-listar/residente-listar.component";
import { ResidenteRoutingModule } from "./residente-routing.module";

@NgModule({
    declarations: [
        ResidenteCadastrarComponent,
        ResidenteListarComponent,
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
        ResidenteRoutingModule,
    ]
})
export class ResidenteModule {}