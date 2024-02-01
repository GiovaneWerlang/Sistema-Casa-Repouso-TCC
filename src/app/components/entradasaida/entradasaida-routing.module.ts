import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AutenticacaoGuard } from '../login/autenticacao.guard';
import { EntradasaidaCadastrarComponent } from './entradasaida-cadastrar/entradasaida-cadastrar.component';
import { EntradasaidaListarComponent } from './entradasaida-listar/entradasaida-listar.component';

const routes: Routes = [
    { path: 'cadastrar', component: EntradasaidaCadastrarComponent, canActivate: [AutenticacaoGuard] },
    { path: 'editar/:id', component: EntradasaidaCadastrarComponent, canActivate: [AutenticacaoGuard] },
    { path: 'listar', component: EntradasaidaListarComponent, canActivate: [AutenticacaoGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EntradaSaidaRoutingModule { }