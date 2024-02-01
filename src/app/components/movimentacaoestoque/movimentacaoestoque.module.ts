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
import { MovimentacaoestoqueCadastrarComponent } from "./movimentacaoestoque-cadastrar/movimentacaoestoque-cadastrar.component";
import { MovimentacaoestoqueListarComponent } from "./movimentacaoestoque-listar/movimentacaoestoque-listar.component";
import { PipesModule } from "src/app/shared/pipesmodule/pipesmodule.module";
import { MovimentacaoEstoqueRoutingModule } from "./movimentacaoestoque-routing.module";

@NgModule({
    declarations: [
        MovimentacaoestoqueCadastrarComponent,
        MovimentacaoestoqueListarComponent,
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
        MovimentacaoEstoqueRoutingModule,
    ]
})
export class MovimentacaoEstoqueModule {}