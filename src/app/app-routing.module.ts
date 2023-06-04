import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { EspecialidadeCadastrarComponent } from './components/especialidade-cadastrar/especialidade-cadastrar.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'especialidade/cadastrar', component: EspecialidadeCadastrarComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
