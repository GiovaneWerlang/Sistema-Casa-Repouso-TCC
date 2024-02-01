import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AutenticacaoGuard } from '../login/autenticacao.guard';
import { ResidenteCadastrarComponent } from './residente-cadastrar/residente-cadastrar.component';
import { ResidenteListarComponent } from './residente-listar/residente-listar.component';

const routes: Routes = [
    { path: 'cadastrar', component: ResidenteCadastrarComponent, canActivate: [AutenticacaoGuard] },
    { path: 'editar/:id', component: ResidenteCadastrarComponent, canActivate: [AutenticacaoGuard] },
    { path: 'listar', component: ResidenteListarComponent, canActivate: [AutenticacaoGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ResidenteRoutingModule { }