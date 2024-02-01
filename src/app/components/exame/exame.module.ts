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
import { ExameCadastrarComponent } from "./exame-cadastrar/exame-cadastrar.component";
import { ExameListarComponent } from "./exame-listar/exame-listar.component";
import { InputTextareaModule } from "primeng/inputtextarea";
import { ExameRoutingModule } from "./exame-routing.module";

@NgModule({
    declarations: [
        ExameCadastrarComponent,
        ExameListarComponent,
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
        InputTextareaModule,
        DropdownModule,
        CalendarModule,
        ExameRoutingModule,
    ]
})
export class ExameModule {}