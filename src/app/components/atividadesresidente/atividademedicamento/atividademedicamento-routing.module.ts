import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AtividademedicamentoEditarComponent } from './atividademedicamento-editar/atividademedicamento-editar.component';
import { AtividademedicamentoListarComponent } from './atividademedicamento-listar/atividademedicamento-listar.component';
import { AutenticacaoGuard } from '../../login/autenticacao.guard';
import { CrudGuard } from 'src/app/shared/crud/crud-guard/crud-guard';

const routes: Routes = [
    { path: 'editar/:id', component: AtividademedicamentoEditarComponent, canActivate: [AutenticacaoGuard], canDeactivate: [CrudGuard] },
    { path: 'listar', component: AtividademedicamentoListarComponent, canActivate: [AutenticacaoGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AtividadeMedicamentoRoutingModule { }