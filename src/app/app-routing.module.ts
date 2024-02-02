import { NgModule, inject } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


import { AutenticacaoGuard } from './components/login/autenticacao.guard';
import { DashboardService } from './components/dashboard/service/dashboard.service';

const routes: Routes = [
  { 
    path: '',
    loadChildren: () => import('./components/home/home.module').then(m => m.HomeModule),
    pathMatch: 'full', 
    canActivate: [AutenticacaoGuard],
    resolve: { resolver: () => inject(DashboardService).list()} },
  { 
    path: 'home',
    loadChildren: () => import('./components/home/home.module').then(m => m.HomeModule),
    canActivate: [AutenticacaoGuard],
    resolve: { resolver: () => inject(DashboardService).list()} },
  {
    path: 'login',
    loadChildren: () => import('./components/login/login.module').then(m => m.LoginModule)
  },
  {
    path: 'especialidade',
    loadChildren: () => import('./components/especialidade/especialidade.module').then(m => m.EspecialidadeModule),
    canActivate: [AutenticacaoGuard]
  },
  {
    path: 'atividadeludica',
    loadChildren: () => import('./components/atividadeludica/atividadeludica.module').then(m => m.AtividadeLudicaModule),
    canActivate: [AutenticacaoGuard]
  },
  {
    path: 'atividadeconsulta',
    loadChildren: () => import('./components/atividadesresidente/atividadeconsulta/atividadeconsulta.module').then(m => m.AtividadeConsultaModule),
    canActivate: [AutenticacaoGuard]
  },
  {
    path: 'atividadeexame',
    loadChildren: () => import('./components/atividadesresidente/atividadeexame/atividadexame.module').then(m => m.AtividadeExameModule),
    canActivate: [AutenticacaoGuard]
  },
  {
    path: 'atividademedicamento',
    loadChildren: () => import('./components/atividadesresidente/atividademedicamento/atividademedicamento.module').then(m => m.AtividadeMedicamentoModule),
    canActivate: [AutenticacaoGuard]
  },
  {
    path: 'profissional',
    loadChildren: () => import('./components/profissional/profissional.module').then(m => m.ProfissionalModule),
    canActivate: [AutenticacaoGuard]
  },
  {
    path: 'residente',
    loadChildren: () => import('./components/residente/residente.module').then(m => m.ResidenteModule),
    canActivate: [AutenticacaoGuard]
  },
  {
    path: 'usuario',
    loadChildren: () => import('./components/usuario/usuario.module').then(m => m.UsuarioModule),
    canActivate: [AutenticacaoGuard]
  },
  {
    path: 'entradasaida',
    loadChildren: () => import('./components/entradasaida/entradasaida.module').then(m => m.EntradaSaidaModule),
    canActivate: [AutenticacaoGuard]
  },
  {
    path: 'exame',
    loadChildren: () => import('./components/exame/exame.module').then(m => m.ExameModule),
    canActivate: [AutenticacaoGuard]
  },
  {
    path: 'consulta',
    loadChildren: () => import('./components/consulta/consulta.module').then(m => m.ConsultaModule),
    canActivate: [AutenticacaoGuard]
  },
  {
    path: 'medicamentoestoque',
    loadChildren: () => import('./components/medicamentoestoque/medicamentoestoque.module').then(m => m.MedicamentoEstoqueModule),
    canActivate: [AutenticacaoGuard]
  },
  {
    path: 'movimentacaoestoque',
    loadChildren: () => import('./components/movimentacaoestoque/movimentacaoestoque.module').then(m => m.MovimentacaoEstoqueModule),
    canActivate: [AutenticacaoGuard]
  },
  {
    path: 'medicamentouso',
    loadChildren: () => import('./components/medicamentouso/medicamentouso.module').then(m => m.MedicamentoUsoModule),
    canActivate: [AutenticacaoGuard]
  },
  {
    path: 'configuracaosistema',
    loadChildren: () => import('./components/configuracaosistema/configuracaosistema.module').then(m => m.ConfiguracaoSistemaModule),
    canActivate: [AutenticacaoGuard]
  },
  {
    path: '**',
    loadChildren: () => import('./components/naoencontrado/naoencontrado.module').then(m => m.NaoEncontradoModule)
  }
];

const config = { useHash: true };

@NgModule({
  imports: [RouterModule.forRoot(routes, config)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
