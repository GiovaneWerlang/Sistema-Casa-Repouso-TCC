import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AutenticacaoGuard } from '../login/autenticacao.guard';
import { ConsultaCadastrarComponent } from './consulta-cadastrar/consulta-cadastrar.component';
import { ConsultaListarComponent } from './consulta-listar/consulta-listar.component';

const routes: Routes = [
    { path: 'cadastrar', component: ConsultaCadastrarComponent, canActivate: [AutenticacaoGuard] },
    { path: 'editar/:id', component: ConsultaCadastrarComponent, canActivate: [AutenticacaoGuard] },
    { path: 'listar', component: ConsultaListarComponent, canActivate: [AutenticacaoGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ConsultaRoutingModule { }