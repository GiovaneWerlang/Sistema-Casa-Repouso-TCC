import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AutenticacaoGuard } from '../login/autenticacao.guard';
import { ProfissionalCadastrarComponent } from './profissional-cadastrar/profissional-cadastrar.component';
import { ProfissionalListarComponent } from './profissional-listar/profissional-listar.component';

const routes: Routes = [
    { path: 'cadastrar', component: ProfissionalCadastrarComponent, canActivate: [AutenticacaoGuard] },
    { path: 'editar/:id', component: ProfissionalCadastrarComponent, canActivate: [AutenticacaoGuard] },
    { path: 'listar', component: ProfissionalListarComponent, canActivate: [AutenticacaoGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfissionalRoutingModule { }