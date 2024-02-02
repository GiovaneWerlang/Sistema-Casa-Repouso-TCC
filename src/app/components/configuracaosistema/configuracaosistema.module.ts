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
import { Routes, RouterModule } from "@angular/router";

const routes: Routes = [
    {
      path: '', component: ConfiguracaosistemaCadastrarComponent
    }
  ];

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
        RouterModule.forChild(routes)
    ],
    exports: [RouterModule]
})
export class ConfiguracaoSistemaModule {}