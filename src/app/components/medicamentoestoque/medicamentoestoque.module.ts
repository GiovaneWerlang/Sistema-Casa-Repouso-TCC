import { NgModule } from "@angular/core";
import { ConfirmDialogModule } from "primeng/confirmdialog";
import { ButtonModule } from "primeng/button";
import { ReactiveFormsModule } from "@angular/forms";
import { TableModule } from "primeng/table";
import { PaginatorModule } from "primeng/paginator";
import { ProgressSpinnerModule } from "primeng/progressspinner";
import { BlockUIModule } from "primeng/blockui";
import { CommonModule } from "@angular/common";
import { InputTextModule } from "primeng/inputtext";
import { CrudTableModule } from "src/app/shared/crud/crud-table/crud-table.module";
import { MedicamentoestoqueCadastrarComponent } from './medicamentoestoque-cadastrar/medicamentoestoque-cadastrar.component';
import { MedicamentoestoqueListarComponent } from './medicamentoestoque-listar/medicamentoestoque-listar.component';
import { MedicamentoEstoqueRoutingModule } from "./medicamentoestoque-routing.module";

@NgModule({
    declarations: [
        MedicamentoestoqueCadastrarComponent,
        MedicamentoestoqueListarComponent,
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
        CrudTableModule,
        MedicamentoEstoqueRoutingModule,
    ]
})
export class MedicamentoEstoqueModule {}