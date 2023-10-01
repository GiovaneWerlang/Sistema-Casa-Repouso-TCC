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

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'especialidade/cadastrar', component: EspecialidadeCadastrarComponent },
  { path: 'especialidade/editar/:id', component: EspecialidadeCadastrarComponent },
  { path: 'especialidade/listar', component: EspecialidadeListarComponent },
  { path: 'atividadeludica/cadastrar', component: AtividadeludicaCadastrarComponent },
  { path: 'atividadeludica/editar/:id', component: AtividadeludicaCadastrarComponent },
  { path: 'atividadeludica/listar', component: AtividadeludicaListarComponent },
  { path: 'profissional/cadastrar', component: ProfissionalCadastrarComponent },
  { path: 'profissional/editar/:id', component: ProfissionalCadastrarComponent },
  { path: 'profissional/listar', component: ProfissionalListarComponent },
  { path: 'residente/cadastrar', component: ResidenteCadastrarComponent },
  { path: 'residente/editar/:id', component: ResidenteCadastrarComponent },
  { path: 'residente/listar', component: ResidenteListarComponent },
  { path: 'usuario/cadastrar', component: UsuarioCadastrarComponent },
  { path: 'usuario/editar/:id', component: UsuarioCadastrarComponent },
  { path: 'usuario/listar', component: UsuarioListarComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
