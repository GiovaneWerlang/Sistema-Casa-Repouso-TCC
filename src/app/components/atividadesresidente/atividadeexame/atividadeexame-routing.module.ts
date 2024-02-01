import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AtividadeexameEditarComponent } from './atividadeexame-editar/atividadeexame-editar.component';
import { AtividadeexameListarComponent } from './atividadeexame-listar/atividadeexame-listar.component';
import { AutenticacaoGuard } from '../../login/autenticacao.guard';

const routes: Routes = [
    { path: 'editar/:id', component: AtividadeexameEditarComponent, canActivate: [AutenticacaoGuard] },
    { path: 'listar', component: AtividadeexameListarComponent, canActivate: [AutenticacaoGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AtividadeExameRoutingModule { }