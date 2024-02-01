import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AutenticacaoGuard } from '../login/autenticacao.guard';
import { AtividadeludicaCadastrarComponent } from './atividadeludica-cadastrar/atividadeludica-cadastrar.component';
import { AtividadeludicaListarComponent } from './atividadeludica-listar/atividadeludica-listar.component';

const routes: Routes = [
    { path: 'cadastrar', component: AtividadeludicaCadastrarComponent, canActivate: [AutenticacaoGuard] },
    { path: 'editar/:id', component: AtividadeludicaCadastrarComponent, canActivate: [AutenticacaoGuard] },
    { path: 'listar', component: AtividadeludicaListarComponent, canActivate: [AutenticacaoGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AtividadeLudicaRoutingModule { }