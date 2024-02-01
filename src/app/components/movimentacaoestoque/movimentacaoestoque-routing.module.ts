import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AutenticacaoGuard } from '../login/autenticacao.guard';
import { MovimentacaoestoqueCadastrarComponent } from './movimentacaoestoque-cadastrar/movimentacaoestoque-cadastrar.component';
import { MovimentacaoestoqueListarComponent } from './movimentacaoestoque-listar/movimentacaoestoque-listar.component';

const routes: Routes = [
    { path: 'cadastrar', component: MovimentacaoestoqueCadastrarComponent, canActivate: [AutenticacaoGuard] },
    { path: 'editar/:id', component: MovimentacaoestoqueCadastrarComponent, canActivate: [AutenticacaoGuard] },
    { path: 'listar', component: MovimentacaoestoqueListarComponent, canActivate: [AutenticacaoGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MovimentacaoEstoqueRoutingModule { }