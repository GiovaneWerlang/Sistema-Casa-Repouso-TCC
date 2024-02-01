import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AutenticacaoGuard } from '../login/autenticacao.guard';
import { EspecialidadeCadastrarComponent } from './especialidade-cadastrar/especialidade-cadastrar.component';
import { EspecialidadeListarComponent } from './especialidade-listar/especialidade-listar.component';

const routes: Routes = [
    { path: 'cadastrar', component: EspecialidadeCadastrarComponent, canActivate: [AutenticacaoGuard] },
    { path: 'editar/:id', component: EspecialidadeCadastrarComponent, canActivate: [AutenticacaoGuard] },
    { path: 'listar', component: EspecialidadeListarComponent, canActivate: [AutenticacaoGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EspecialidadeRoutingModule { }