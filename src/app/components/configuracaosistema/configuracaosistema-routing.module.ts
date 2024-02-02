import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { ConfiguracaosistemaCadastrarComponent } from "./configuracaosistema-cadastrar/configuracaosistema-cadastrar.component";

const routes: Routes = [
    {
      path: '', component: ConfiguracaosistemaCadastrarComponent
    }
  ];

@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [RouterModule]
})
export class ConfiguracaoSistemaRoutingModule {}