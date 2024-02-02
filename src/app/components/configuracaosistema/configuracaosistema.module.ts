import { NgModule } from "@angular/core";
import { ConfirmDialogModule } from "primeng/confirmdialog";
import { ButtonModule } from "primeng/button";
import { ReactiveFormsModule } from "@angular/forms";
import { ProgressSpinnerModule } from "primeng/progressspinner";
import { BlockUIModule } from "primeng/blockui";
import { CommonModule } from "@angular/common";
import { InputTextModule } from "primeng/inputtext";
import { CheckboxModule } from "primeng/checkbox";
import { ConfiguracaosistemaCadastrarComponent } from "./configuracaosistema-cadastrar/configuracaosistema-cadastrar.component";
import { ConfiguracaoSistemaRoutingModule } from "./configuracaosistema-routing.module";

@NgModule({
    declarations: [
        ConfiguracaosistemaCadastrarComponent
    ],
    imports: [
        CommonModule,
        ReactiveFormsModule,
        ButtonModule,
        ConfirmDialogModule,
        ProgressSpinnerModule,
        BlockUIModule,
        InputTextModule,
        CheckboxModule,
        ConfiguracaoSistemaRoutingModule
    ]
})
export class ConfiguracaoSistemaModule {}