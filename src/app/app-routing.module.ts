import { Exame } from './components/exame/modelo/exame';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { EspecialidadeCadastrarComponent } from './components/especialidade/especialidade-cadastrar/especialidade-cadastrar.component';
import { EspecialidadeListarComponent } from './components/especialidade/especialidade-listar/especialidade-listar.component';
import { AtividadeludicaListarComponent } from './components/atividadeludica/atividadeludica-listar/atividadeludica-listar.component';
import { AtividadeludicaCadastrarComponent } from './components/atividadeludica/atividadeludica-cadastrar/atividadeludica-cadastrar.component';
import { ProfissionalListarComponent } from './components/profissional/profissional-listar/profissional-listar.component';
import { ProfissionalCadastrarComponent } from './components/profissional/profissional-cadastrar/profissional-cadastrar.component';
import { ResidenteListarComponent } from './components/residente/residente-listar/residente-listar.component';
import { ResidenteCadastrarComponent } from './components/residente/residente-cadastrar/residente-cadastrar.component';
import { UsuarioListarComponent } from './components/usuario/usuario-listar/usuario-listar.component';
import { UsuarioCadastrarComponent } from './components/usuario/usuario-cadastrar/usuario-cadastrar.component';
import { EntradasaidaListarComponent } from './components/entradasaida/entradasaida-listar/entradasaida-listar.component';
import { EntradasaidaCadastrarComponent } from './components/entradasaida/entradasaida-cadastrar/entradasaida-cadastrar.component';
import { ExameListarComponent } from './components/exame/exame-listar/exame-listar.component';
import { ExameCadastrarComponent } from './components/exame/exame-cadastrar/exame-cadastrar.component';
import { ConsultaListarComponent } from './components/consulta/consulta-listar/consulta-listar.component';
import { ConsultaCadastrarComponent } from './components/consulta/consulta-cadastrar/consulta-cadastrar.component';
import { MedicamentoestoqueListarComponent } from './components/medicamentoestoque/medicamentoestoque-listar/medicamentoestoque-listar.component';
import { MedicamentoestoqueCadastrarComponent } from './components/medicamentoestoque/medicamentoestoque-cadastrar/medicamentoestoque-cadastrar.component';
import { MovimentacaoestoqueListarComponent } from './components/movimentacaoestoque/movimentacaoestoque-listar/movimentacaoestoque-listar.component';
import { MovimentacaoestoqueCadastrarComponent } from './components/movimentacaoestoque/movimentacaoestoque-cadastrar/movimentacaoestoque-cadastrar.component';
import { MedicamentousoListarComponent } from './components/medicamentouso/medicamentouso-listar/medicamentouso-listar.component';
import { MedicamentousoCadastrarComponent } from './components/medicamentouso/medicamentouso-cadastrar/medicamentouso-cadastrar.component';

import { AtividademedicamentoListarComponent } from './components/atividadesresidente/atividademedicamento/atividademedicamento-listar/atividademedicamento-listar.component';
import { AtividademedicamentoEditarComponent } from './components/atividadesresidente/atividademedicamento/atividademedicamento-editar/atividademedicamento-editar.component';
import { AtividadeconsultaEditarComponent } from './components/atividadesresidente/atividadeconsulta/atividadeconsulta-editar/atividadeconsulta-editar.component';
import { AtividadeconsultaListarComponent } from './components/atividadesresidente/atividadeconsulta/atividadeconsulta-listar/atividadeconsulta-listar.component';
import { AtividadeexameListarComponent } from './components/atividadesresidente/atividadeexame/atividadeexame-listar/atividadeexame-listar.component';
import { AtividadeexameEditarComponent } from './components/atividadesresidente/atividadeexame/atividadeexame-editar/atividadeexame-editar.component';

import { LoginComponent } from './components/login/login.component';
import { NaoencontradoComponent } from './components/naoencontrado/naoencontrado.component';
import { AutenticacaoGuard } from './components/login/autenticacao.guard';

const routes: Routes = [
  { path: '', component: HomeComponent, canActivate: [AutenticacaoGuard], pathMatch: 'full' },
  { path: 'home', component: HomeComponent, canActivate: [AutenticacaoGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'especialidade/cadastrar', component: EspecialidadeCadastrarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'especialidade/editar/:id', component: EspecialidadeCadastrarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'especialidade/listar', component: EspecialidadeListarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'atividadeludica/cadastrar', component: AtividadeludicaCadastrarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'atividadeludica/editar/:id', component: AtividadeludicaCadastrarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'atividadeludica/listar', component: AtividadeludicaListarComponent, canActivate: [AutenticacaoGuard] },

  { path: 'atividadeconsulta/editar/:id', component: AtividadeconsultaEditarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'atividadeconsulta/listar', component: AtividadeconsultaListarComponent, canActivate: [AutenticacaoGuard] },

  { path: 'atividadeexame/editar/:id', component: AtividadeexameEditarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'atividadeexame/listar', component: AtividadeexameListarComponent, canActivate: [AutenticacaoGuard] },

  { path: 'atividademedicamento/editar/:id', component: AtividademedicamentoEditarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'atividademedicamento/listar', component: AtividademedicamentoListarComponent, canActivate: [AutenticacaoGuard] },

  { path: 'profissional/cadastrar', component: ProfissionalCadastrarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'profissional/editar/:id', component: ProfissionalCadastrarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'profissional/listar', component: ProfissionalListarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'residente/cadastrar', component: ResidenteCadastrarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'residente/editar/:id', component: ResidenteCadastrarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'residente/listar', component: ResidenteListarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'usuario/cadastrar', component: UsuarioCadastrarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'usuario/editar/:id', component: UsuarioCadastrarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'usuario/listar', component: UsuarioListarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'entradasaida/cadastrar', component: EntradasaidaCadastrarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'entradasaida/editar/:id', component: EntradasaidaCadastrarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'entradasaida/listar', component: EntradasaidaListarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'exame/cadastrar', component: ExameCadastrarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'exame/editar/:id', component: ExameCadastrarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'exame/listar', component: ExameListarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'consulta/cadastrar', component: ConsultaCadastrarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'consulta/editar/:id', component: ConsultaCadastrarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'consulta/listar', component: ConsultaListarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'medicamentoestoque/cadastrar', component: MedicamentoestoqueCadastrarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'medicamentoestoque/editar/:id', component: MedicamentoestoqueCadastrarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'medicamentoestoque/listar', component: MedicamentoestoqueListarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'movimentacaoestoque/cadastrar', component: MovimentacaoestoqueCadastrarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'movimentacaoestoque/editar/:id', component: MovimentacaoestoqueCadastrarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'movimentacaoestoque/listar', component: MovimentacaoestoqueListarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'medicamentouso/cadastrar', component: MedicamentousoCadastrarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'medicamentouso/editar/:id', component: MedicamentousoCadastrarComponent, canActivate: [AutenticacaoGuard] },
  { path: 'medicamentouso/listar', component: MedicamentousoListarComponent, canActivate: [AutenticacaoGuard] },
  { path: '**', component: NaoencontradoComponent },
];

const config = {useHash:true};

@NgModule({
  imports: [RouterModule.forRoot(routes,config)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
