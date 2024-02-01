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
import { UsuarioCadastrarComponent } from "./usuario-cadastrar/usuario-cadastrar.component";
import { UsuarioListarComponent } from "./usuario-listar/usuario-listar.component";
import { PipesModule } from "src/app/shared/pipesmodule/pipesmodule.module";
import { UsuarioRoutingModule } from "./usuario-routing.module";

@NgModule({
    declarations: [
        UsuarioCadastrarComponent,
        UsuarioListarComponent,
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
        UsuarioRoutingModule,
    ]
})
export class UsuarioModule {}