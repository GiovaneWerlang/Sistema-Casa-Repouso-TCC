import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { NaoencontradoComponent } from "./naoencontrado.component";

const routes: Routes = [
    { path: '', component: NaoencontradoComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class NaoEncontradoRoutingModule { }