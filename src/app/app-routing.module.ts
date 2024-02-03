import { NgModule, inject } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AutenticacaoGuard } from './components/login/autenticacao.guard';
import { DashboardService } from './components/dashboard/service/dashboard.service';
import { RolesGuard } from './components/login/roles.guard';

const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./components/home/home.module').then(m => m.HomeModule),
    pathMatch: 'full',
    canActivate: [AutenticacaoGuard, RolesGuard],
    data: { roles: ['ADMIN', 'CUIDADOR', 'ENFERMEIRO', 'FUNCIONARIO', 'MEDICO', 'VOLUNTARIO'] },
    resolve: { resolver: () => inject(DashboardService).list() }
  },
  {
    path: 'home',
    loadChildren: () => import('./components/home/home.module').then(m => m.HomeModule),
    canActivate: [AutenticacaoGuard, RolesGuard],
    data: { roles: ['ADMIN', 'CUIDADOR', 'ENFERMEIRO', 'FUNCIONARIO', 'MEDICO', 'VOLUNTARIO'] },
    resolve: { resolver: () => inject(DashboardService).list() }
  },
  {
    path: 'login',
    loadChildren: () => import('./components/login/login.module').then(m => m.LoginModule)
  },
  {
    path: 'especialidade',
    loadChildren: () => import('./components/especialidade/especialidade.module').then(m => m.EspecialidadeModule),
    canActivate: [AutenticacaoGuard, RolesGuard],
    data: { roles: ['ADMIN', 'CUIDADOR', 'ENFERMEIRO', 'FUNCIONARIO', 'MEDICO', 'VOLUNTARIO'] }
  },
  {
    path: 'atividadeludica',
    loadChildren: () => import('./components/atividadeludica/atividadeludica.module').then(m => m.AtividadeLudicaModule),
    canActivate: [AutenticacaoGuard, RolesGuard],
    data: { roles: ['ADMIN', 'CUIDADOR', 'FUNCIONARIO', 'VOLUNTARIO'] }
  },
  {
    path: 'atividadeconsulta',
    loadChildren: () => import('./components/atividadesresidente/atividadeconsulta/atividadeconsulta.module').then(m => m.AtividadeConsultaModule),
    canActivate: [AutenticacaoGuard, RolesGuard],
    data: { roles: ['ADMIN', 'CUIDADOR', 'VOLUNTARIO'] }
  },
  {
    path: 'atividadeexame',
    loadChildren: () => import('./components/atividadesresidente/atividadeexame/atividadexame.module').then(m => m.AtividadeExameModule),
    canActivate: [AutenticacaoGuard, RolesGuard],
    data: { roles: ['ADMIN', 'CUIDADOR', 'VOLUNTARIO'] }
  },
  {
    path: 'atividademedicamento',
    loadChildren: () => import('./components/atividadesresidente/atividademedicamento/atividademedicamento.module').then(m => m.AtividadeMedicamentoModule),
    canActivate: [AutenticacaoGuard, RolesGuard],
    data: { roles: ['ADMIN', 'CUIDADOR', 'VOLUNTARIO'] }
  },
  {
    path: 'profissional',
    loadChildren: () => import('./components/profissional/profissional.module').then(m => m.ProfissionalModule),
    canActivate: [AutenticacaoGuard, RolesGuard],
    data: { roles: ['ADMIN', 'FUNCIONARIO'] }
  },
  {
    path: 'residente',
    loadChildren: () => import('./components/residente/residente.module').then(m => m.ResidenteModule),
    canActivate: [AutenticacaoGuard, RolesGuard],
    data: { roles: ['ADMIN', 'CUIDADOR', 'VOLUNTARIO'] }
  },
  {
    path: 'usuario',
    loadChildren: () => import('./components/usuario/usuario.module').then(m => m.UsuarioModule),
    canActivate: [AutenticacaoGuard, RolesGuard],
    data: { roles: ['ADMIN', 'FUNCIONARIO'] }
  },
  {
    path: 'entradasaida',
    loadChildren: () => import('./components/entradasaida/entradasaida.module').then(m => m.EntradaSaidaModule),
    canActivate: [AutenticacaoGuard, RolesGuard],
    data: { roles: ['ADMIN', 'CUIDADOR', 'VOLUNTARIO'] }
  },
  {
    path: 'exame',
    loadChildren: () => import('./components/exame/exame.module').then(m => m.ExameModule),
    canActivate: [AutenticacaoGuard, RolesGuard],
    data: { roles: ['ADMIN', 'CUIDADOR', 'ENFERMEIRO', 'MEDICO'] }
  },
  {
    path: 'consulta',
    loadChildren: () => import('./components/consulta/consulta.module').then(m => m.ConsultaModule),
    canActivate: [AutenticacaoGuard, RolesGuard],
    data: { roles: ['ADMIN', 'CUIDADOR', 'ENFERMEIRO', 'MEDICO'] }
  },
  {
    path: 'medicamentoestoque',
    loadChildren: () => import('./components/medicamentoestoque/medicamentoestoque.module').then(m => m.MedicamentoEstoqueModule),
    canActivate: [AutenticacaoGuard, RolesGuard],
    data: { roles: ['ADMIN', 'CUIDADOR', 'ENFERMEIRO', 'MEDICO'] }
  },
  {
    path: 'movimentacaoestoque',
    loadChildren: () => import('./components/movimentacaoestoque/movimentacaoestoque.module').then(m => m.MovimentacaoEstoqueModule),
    canActivate: [AutenticacaoGuard, RolesGuard],
    data: { roles: ['ADMIN', 'CUIDADOR', 'FUNCIONARIO'] }
  },
  {
    path: 'medicamentouso',
    loadChildren: () => import('./components/medicamentouso/medicamentouso.module').then(m => m.MedicamentoUsoModule),
    canActivate: [AutenticacaoGuard, RolesGuard],
    data: { roles: ['ADMIN', 'CUIDADOR', 'ENFERMEIRO', 'MEDICO', 'VOLUNTARIO'] }
  },
  {
    path: 'configuracaosistema',
    loadChildren: () => import('./components/configuracaosistema/configuracaosistema.module').then(m => m.ConfiguracaoSistemaModule),
    canActivate: [AutenticacaoGuard, RolesGuard],
    data: { roles: ['ADMIN', 'FUNCIONARIO'] }
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
