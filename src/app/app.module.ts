import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './components/app-root/app.component';
import { HomeComponent } from './components/home/home.component';
import { EspecialidadeCadastrarComponent } from './components/especialidade/especialidade-cadastrar/especialidade-cadastrar.component';
import { EspecialidadeListarComponent } from './components/especialidade/especialidade-listar/especialidade-listar.component';
import { SideMenuComponent } from './components/side-menu/side-menu.component';
import { AtividadeludicaCadastrarComponent } from './components/atividadeludica/atividadeludica-cadastrar/atividadeludica-cadastrar.component';
import { AtividadeludicaListarComponent } from './components/atividadeludica/atividadeludica-listar/atividadeludica-listar.component';

import { MenuModule } from 'primeng/menu';
import { InputTextModule } from 'primeng/inputtext';
import { SidebarModule } from 'primeng/sidebar';
import { TableModule } from 'primeng/table';
import { DividerModule } from 'primeng/divider';
import { CrudTableComponent } from './shared/crud-table/crud-table/crud-table.component';
import { HeaderComponent } from './components/header/header.component';
import { TooltipModule } from 'primeng/tooltip';
import { MenuComponent } from './components/menu/menu.component';
import { PaginatorModule } from 'primeng/paginator';
import { CalendarModule } from 'primeng/calendar';
import { DropdownModule } from 'primeng/dropdown';
import { ButtonModule } from 'primeng/button';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    EspecialidadeCadastrarComponent,
    EspecialidadeListarComponent,
    SideMenuComponent,
    CrudTableComponent,
    HeaderComponent,
    MenuComponent,
    AtividadeludicaListarComponent,
    AtividadeludicaCadastrarComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    ButtonModule,
    MenuModule,
    InputTextModule,
    SidebarModule,
    TableModule,
    DividerModule,
    TooltipModule,
    PaginatorModule,
    CalendarModule,
    DropdownModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
