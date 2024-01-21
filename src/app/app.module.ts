import { ConfiguracaosistemaCadastrarComponent } from './components/configuracaosistema/configuracaosistema-cadastrar/configuracaosistema-cadastrar.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './components/app-root/app.component';
import { HomeComponent } from './components/home/home.component';
import { EspecialidadeCadastrarComponent } from './components/especialidade/especialidade-cadastrar/especialidade-cadastrar.component';
import { EspecialidadeListarComponent } from './components/especialidade/especialidade-listar/especialidade-listar.component';
import { SideMenuComponent } from './components/side-menu/side-menu.component';
import { AtividadeludicaCadastrarComponent } from './components/atividadeludica/atividadeludica-cadastrar/atividadeludica-cadastrar.component';
import { AtividadeludicaListarComponent } from './components/atividadeludica/atividadeludica-listar/atividadeludica-listar.component';
import { ProfissionalListarComponent } from './components/profissional/profissional-listar/profissional-listar.component';
import { ProfissionalCadastrarComponent } from './components/profissional/profissional-cadastrar/profissional-cadastrar.component';

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
import { KeyFilterModule } from 'primeng/keyfilter';
import { InputMaskModule } from 'primeng/inputmask';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { ToastModule } from 'primeng/toast';
import { CardModule } from 'primeng/card';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { BlockUIModule } from 'primeng/blockui';
import { ChartModule } from 'primeng/chart';
import { CheckboxModule } from 'primeng/checkbox';

import { NgxMaskDirective, NgxMaskPipe, provideNgxMask } from 'ngx-mask';

import { EnumPipe } from './shared/enum-pipe/enum-pipe.pipe';
import { CpfPipe } from './shared/cpf-pipe/cpf-pipe.pipe';
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
import { LoginComponent } from './components/login/login.component';
import { NaoencontradoComponent } from './components/naoencontrado/naoencontrado.component';
import { MessageService } from 'primeng/api';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AutenticacaoInterceptorService } from './components/login/autenticacaointerceptor.service';
import { AtividademedicamentoListarComponent } from './components/atividadesresidente/atividademedicamento/atividademedicamento-listar/atividademedicamento-listar.component';
import { AtividademedicamentoEditarComponent } from './components/atividadesresidente/atividademedicamento/atividademedicamento-editar/atividademedicamento-editar.component';
import { AtividadeconsultaEditarComponent } from './components/atividadesresidente/atividadeconsulta/atividadeconsulta-editar/atividadeconsulta-editar.component';
import { AtividadeconsultaListarComponent } from './components/atividadesresidente/atividadeconsulta/atividadeconsulta-listar/atividadeconsulta-listar.component';
import { AtividadeexameListarComponent } from './components/atividadesresidente/atividadeexame/atividadeexame-listar/atividadeexame-listar.component';
import { AtividadeexameEditarComponent } from './components/atividadesresidente/atividadeexame/atividadeexame-editar/atividadeexame-editar.component';
import { DataHoraInterceptorService } from './shared/crud-service/datahorainterceptor.service';

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
    ProfissionalListarComponent,
    EnumPipe,
    CpfPipe,
    ProfissionalCadastrarComponent,
    ResidenteListarComponent,
    ResidenteCadastrarComponent,
    UsuarioListarComponent,
    UsuarioCadastrarComponent,
    EntradasaidaListarComponent,
    EntradasaidaCadastrarComponent,
    ExameListarComponent,
    ExameCadastrarComponent,
    ConsultaListarComponent,
    ConsultaCadastrarComponent,
    MedicamentoestoqueListarComponent,
    MedicamentoestoqueCadastrarComponent,
    MovimentacaoestoqueListarComponent,
    MovimentacaoestoqueCadastrarComponent,
    MedicamentousoListarComponent,
    MedicamentousoCadastrarComponent,
    LoginComponent,
    NaoencontradoComponent,
    DashboardComponent,
    AtividademedicamentoListarComponent,
    AtividademedicamentoEditarComponent,
    AtividadeconsultaEditarComponent,
    AtividadeconsultaListarComponent,
    AtividadeexameListarComponent,
    AtividadeexameEditarComponent,
    ConfiguracaosistemaCadastrarComponent
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
    KeyFilterModule,
    InputMaskModule,
    InputTextareaModule,
    ToastModule,
    CardModule,
    ProgressSpinnerModule,
    BlockUIModule,
    ChartModule,
    NgxMaskDirective,
    NgxMaskPipe,
    CheckboxModule,
  ],
  providers: [
    MessageService, 
    {provide: HTTP_INTERCEPTORS, useClass: AutenticacaoInterceptorService, multi: true},
    { provide: HTTP_INTERCEPTORS, useClass: DataHoraInterceptorService, multi: true },
    provideNgxMask()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
