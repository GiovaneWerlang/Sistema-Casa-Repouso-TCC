import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { ConfiguracaosistemaCadastrarComponent } from "./configuracaosistema-cadastrar/configuracaosistema-cadastrar.component";
import { CrudGuard } from "src/app/shared/crud/crud-guard/crud-guard";

const routes: Routes = [
    {
      path: '', component: ConfiguracaosistemaCadastrarComponent, canDeactivate: [CrudGuard]
    }
  ];

@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [RouterModule]
})
export class ConfiguracaoSistemaRoutingModule {}