import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { EspecialidadeCadastrarComponent } from './components/especialidade/especialidade-cadastrar/especialidade-cadastrar.component';
import { EspecialidadeListarComponent } from './components/especialidade/especialidade-listar/especialidade-listar.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'especialidade/cadastrar', component: EspecialidadeCadastrarComponent },
  { path: 'especialidade/editar/:id', component: EspecialidadeCadastrarComponent },
  { path: 'especialidade/listar', component: EspecialidadeListarComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
