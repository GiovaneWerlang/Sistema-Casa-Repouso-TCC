import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AutenticacaoGuard } from '../login/autenticacao.guard';
import { EntradasaidaCadastrarComponent } from './entradasaida-cadastrar/entradasaida-cadastrar.component';
import { EntradasaidaListarComponent } from './entradasaida-listar/entradasaida-listar.component';
import { CrudGuard } from 'src/app/shared/crud/crud-guard/crud-guard';

const routes: Routes = [
    { path: 'cadastrar', component: EntradasaidaCadastrarComponent, canActivate: [AutenticacaoGuard], canDeactivate: [CrudGuard] },
    { path: 'editar/:id', component: EntradasaidaCadastrarComponent, canActivate: [AutenticacaoGuard], canDeactivate: [CrudGuard] },
    { path: 'listar', component: EntradasaidaListarComponent, canActivate: [AutenticacaoGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EntradaSaidaRoutingModule { }