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
import { EspecialidadeCadastrarComponent } from "./especialidade-cadastrar/especialidade-cadastrar.component";
import { EspecialidadeListarComponent } from "./especialidade-listar/especialidade-listar.component";
import { CrudTableModule } from "src/app/shared/crud/crud-table/crud-table.module";
import { EspecialidadeRoutingModule } from "./especialidade-routing.module";

@NgModule({
    declarations: [
        EspecialidadeCadastrarComponent,
        EspecialidadeListarComponent,
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
        EspecialidadeRoutingModule,
    ]
})
export class EspecialidadeModule {}