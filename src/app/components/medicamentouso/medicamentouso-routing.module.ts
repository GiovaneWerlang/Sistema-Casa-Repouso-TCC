import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AutenticacaoGuard } from '../login/autenticacao.guard';
import { MedicamentousoCadastrarComponent } from './medicamentouso-cadastrar/medicamentouso-cadastrar.component';
import { MedicamentousoListarComponent } from './medicamentouso-listar/medicamentouso-listar.component';

const routes: Routes = [
    { path: 'cadastrar', component: MedicamentousoCadastrarComponent, canActivate: [AutenticacaoGuard] },
    { path: 'editar/:id', component: MedicamentousoCadastrarComponent, canActivate: [AutenticacaoGuard] },
    { path: 'listar', component: MedicamentousoListarComponent, canActivate: [AutenticacaoGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MedicamentoUsoRoutingModule { }