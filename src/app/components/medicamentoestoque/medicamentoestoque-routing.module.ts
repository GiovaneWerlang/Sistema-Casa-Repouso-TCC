import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AutenticacaoGuard } from '../login/autenticacao.guard';
import { MedicamentoestoqueCadastrarComponent } from './medicamentoestoque-cadastrar/medicamentoestoque-cadastrar.component';
import { MedicamentoestoqueListarComponent } from './medicamentoestoque-listar/medicamentoestoque-listar.component';
import { CrudGuard } from 'src/app/shared/crud/crud-guard/crud-guard';

const routes: Routes = [
    { path: 'cadastrar', component: MedicamentoestoqueCadastrarComponent, canActivate: [AutenticacaoGuard], canDeactivate: [CrudGuard] },
    { path: 'editar/:id', component: MedicamentoestoqueCadastrarComponent, canActivate: [AutenticacaoGuard], canDeactivate: [CrudGuard] },
    { path: 'listar', component: MedicamentoestoqueListarComponent, canActivate: [AutenticacaoGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MedicamentoEstoqueRoutingModule { }