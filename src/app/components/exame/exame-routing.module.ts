import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AutenticacaoGuard } from '../login/autenticacao.guard';
import { ExameCadastrarComponent } from './exame-cadastrar/exame-cadastrar.component';
import { ExameListarComponent } from './exame-listar/exame-listar.component';
import { CrudGuard } from 'src/app/shared/crud/crud-guard/crud-guard';

const routes: Routes = [
    { path: 'cadastrar', component: ExameCadastrarComponent, canActivate: [AutenticacaoGuard], canDeactivate: [CrudGuard] },
    { path: 'editar/:id', component: ExameCadastrarComponent, canActivate: [AutenticacaoGuard], canDeactivate: [CrudGuard] },
    { path: 'listar', component: ExameListarComponent, canActivate: [AutenticacaoGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ExameRoutingModule { }