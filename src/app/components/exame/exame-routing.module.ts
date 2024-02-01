import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AutenticacaoGuard } from '../login/autenticacao.guard';
import { ExameCadastrarComponent } from './exame-cadastrar/exame-cadastrar.component';
import { ExameListarComponent } from './exame-listar/exame-listar.component';

const routes: Routes = [
    { path: 'cadastrar', component: ExameCadastrarComponent, canActivate: [AutenticacaoGuard] },
    { path: 'editar/:id', component: ExameCadastrarComponent, canActivate: [AutenticacaoGuard] },
    { path: 'listar', component: ExameListarComponent, canActivate: [AutenticacaoGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ExameRoutingModule { }