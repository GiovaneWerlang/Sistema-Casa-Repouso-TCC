import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AutenticacaoGuard } from '../../login/autenticacao.guard';
import { AtividadeconsultaEditarComponent } from './atividadeconsulta-editar/atividadeconsulta-editar.component';
import { AtividadeconsultaListarComponent } from './atividadeconsulta-listar/atividadeconsulta-listar.component';

const routes: Routes = [
    { path: 'editar/:id', component: AtividadeconsultaEditarComponent, canActivate: [AutenticacaoGuard] },
    { path: 'listar', component: AtividadeconsultaListarComponent, canActivate: [AutenticacaoGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AtividadeConsultaRoutingModule { }