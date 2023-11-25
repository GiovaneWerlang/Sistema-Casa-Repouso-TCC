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
  { path: 'especialidade/cadastrar', component: EspecialidadeCadastrarComponent },
  { path: 'especialidade/editar/:id', component: EspecialidadeCadastrarComponent },
  { path: 'especialidade/listar', component: EspecialidadeListarComponent },
  { path: 'atividadeludica/cadastrar', component: AtividadeludicaCadastrarComponent },
  { path: 'atividadeludica/editar/:id', component: AtividadeludicaCadastrarComponent },
  { path: 'atividadeludica/listar', component: AtividadeludicaListarComponent },

  { path: 'atividadeconsulta/editar/:id', component: AtividadeconsultaEditarComponent },
  { path: 'atividadeconsulta/listar', component: AtividadeconsultaListarComponent },

  { path: 'atividadeexame/editar/:id', component: AtividadeexameEditarComponent },
  { path: 'atividadeexame/listar', component: AtividadeexameListarComponent },

  { path: 'atividademedicamento/editar/:id', component: AtividademedicamentoEditarComponent },
  { path: 'atividademedicamento/listar', component: AtividademedicamentoListarComponent },

  { path: 'profissional/cadastrar', component: ProfissionalCadastrarComponent },
  { path: 'profissional/editar/:id', component: ProfissionalCadastrarComponent },
  { path: 'profissional/listar', component: ProfissionalListarComponent },
  { path: 'residente/cadastrar', component: ResidenteCadastrarComponent },
  { path: 'residente/editar/:id', component: ResidenteCadastrarComponent },
  { path: 'residente/listar', component: ResidenteListarComponent },
  { path: 'usuario/cadastrar', component: UsuarioCadastrarComponent },
  { path: 'usuario/editar/:id', component: UsuarioCadastrarComponent },
  { path: 'usuario/listar', component: UsuarioListarComponent },
  { path: 'entradasaida/cadastrar', component: EntradasaidaCadastrarComponent },
  { path: 'entradasaida/editar/:id', component: EntradasaidaCadastrarComponent },
  { path: 'entradasaida/listar', component: EntradasaidaListarComponent},
  { path: 'exame/cadastrar', component: ExameCadastrarComponent },
  { path: 'exame/editar/:id', component: ExameCadastrarComponent },
  { path: 'exame/listar', component: ExameListarComponent},
  { path: 'consulta/cadastrar', component: ConsultaCadastrarComponent },
  { path: 'consulta/editar/:id', component: ConsultaCadastrarComponent },
  { path: 'consulta/listar', component: ConsultaListarComponent},
  { path: 'medicamentoestoque/cadastrar', component: MedicamentoestoqueCadastrarComponent },
  { path: 'medicamentoestoque/editar/:id', component: MedicamentoestoqueCadastrarComponent },
  { path: 'medicamentoestoque/listar', component: MedicamentoestoqueListarComponent},
  { path: 'movimentacaoestoque/cadastrar', component: MovimentacaoestoqueCadastrarComponent },
  { path: 'movimentacaoestoque/editar/:id', component: MovimentacaoestoqueCadastrarComponent },
  { path: 'movimentacaoestoque/listar', component: MovimentacaoestoqueListarComponent},
  { path: 'medicamentouso/cadastrar', component: MedicamentousoCadastrarComponent },
  { path: 'medicamentouso/editar/:id', component: MedicamentousoCadastrarComponent },
  { path: 'medicamentouso/listar', component: MedicamentousoListarComponent},
  { path: '**', component: NaoencontradoComponent },
];

const config = {useHash:true};

@NgModule({
  imports: [RouterModule.forRoot(routes,config)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
