import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { EspecialidadeCadastrarComponent } from './components/especialidade/especialidade-cadastrar/especialidade-cadastrar.component';
import { EspecialidadeListarComponent } from './components/especialidade/especialidade-listar/especialidade-listar.component';
import { AtividadeludicaListarComponent } from './components/atividadeludica/atividadeludica-listar/atividadeludica-listar.component';
import { AtividadeludicaCadastrarComponent } from './components/atividadeludica/atividadeludica-cadastrar/atividadeludica-cadastrar.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'especialidade/cadastrar', component: EspecialidadeCadastrarComponent },
  { path: 'especialidade/editar/:id', component: EspecialidadeCadastrarComponent },
  { path: 'especialidade/listar', component: EspecialidadeListarComponent },
  { path: 'atividadeludica/cadastrar', component: AtividadeludicaCadastrarComponent },
  { path: 'atividadeludica/editar/:id', component: AtividadeludicaCadastrarComponent },
  { path: 'atividadeludica/listar', component: AtividadeludicaListarComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
