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
import { EntradasaidaCadastrarComponent } from "./entradasaida-cadastrar/entradasaida-cadastrar.component";
import { EntradasaidaListarComponent } from "./entradasaida-listar/entradasaida-listar.component";
import { EntradaSaidaRoutingModule } from "./entradasaida-routing.module";

@NgModule({
    declarations: [
        EntradasaidaCadastrarComponent,
        EntradasaidaListarComponent,
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
        EntradaSaidaRoutingModule,
    ]
})
export class EntradaSaidaModule {}