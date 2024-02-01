import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AutenticacaoGuard } from '../login/autenticacao.guard';
import { UsuarioCadastrarComponent } from './usuario-cadastrar/usuario-cadastrar.component';
import { UsuarioListarComponent } from './usuario-listar/usuario-listar.component';

const routes: Routes = [
    { path: 'cadastrar', component: UsuarioCadastrarComponent, canActivate: [AutenticacaoGuard] },
    { path: 'editar/:id', component: UsuarioCadastrarComponent, canActivate: [AutenticacaoGuard] },
    { path: 'listar', component: UsuarioListarComponent, canActivate: [AutenticacaoGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsuarioRoutingModule { }