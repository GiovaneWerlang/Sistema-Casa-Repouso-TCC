import { NgModule, isDevMode } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './components/app-root/app.component';

import { MenuModule } from 'primeng/menu';
import { InputTextModule } from 'primeng/inputtext';
import { SidebarModule } from 'primeng/sidebar';
import { TableModule } from 'primeng/table';
import { DividerModule } from 'primeng/divider';
import { TooltipModule } from 'primeng/tooltip';
import { ButtonModule } from 'primeng/button';
import { ToastModule } from 'primeng/toast';
import { CardModule } from 'primeng/card';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { BlockUIModule } from 'primeng/blockui';
import { ChartModule } from 'primeng/chart';
import { MessageService } from 'primeng/api';

import { provideNgxMask } from 'ngx-mask';

import { AutenticacaoInterceptorService } from './components/login/autenticacaointerceptor.service';
import { DataHoraInterceptorService } from './shared/crud/crud-service/datahorainterceptor.service';

import { ServiceWorkerModule } from '@angular/service-worker';

import { AtividadeLudicaModule } from './components/atividadeludica/atividadeludica.module';
import { AtividadeConsultaModule } from './components/atividadesresidente/atividadeconsulta/atividadeconsulta.module';
import { AtividadeExameModule } from './components/atividadesresidente/atividadeexame/atividadexame.module';
import { AtividadeMedicamentoModule } from './components/atividadesresidente/atividademedicamento/atividademedicamento.module';
import { ConsultaModule } from './components/consulta/consulta.module';
import { ConfiguracaoSistemaModule } from './components/configuracaosistema/configuracaosistema.module';
import { EntradaSaidaModule } from './components/entradasaida/entradasaida.module';
import { EspecialidadeModule } from './components/especialidade/especialidade.module';
import { MedicamentoEstoqueModule } from './components/medicamentoestoque/medicamentoestoque.module';
import { ExameModule } from './components/exame/exame.module';
import { MedicamentoUsoModule } from './components/medicamentouso/medicamentouso.module';
import { MovimentacaoEstoqueModule } from './components/movimentacaoestoque/movimentacaoestoque.module';
import { ProfissionalModule } from './components/profissional/profissional.module';
import { ResidenteModule } from './components/residente/residente.module';
import { UsuarioModule } from './components/usuario/usuario.module';
import { NaoEncontradoModule } from './components/naoencontrado/naoencontrado.module';
import { LoginModule } from './components/login/login.module';
import { HomeModule } from './components/home/home.module';
import { HeaderModule } from './components/header/header.module';
import { MenuItemsModule } from './components/menu/menu.module';

@NgModule({
    declarations: [
        AppComponent,
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
        ToastModule,
        CardModule,
        ProgressSpinnerModule,
        BlockUIModule,
        ChartModule,
        ServiceWorkerModule.register('ngsw-worker.js', {
          enabled: !isDevMode(),
          // Register the ServiceWorker as soon as the application is stable
          // or after 30 seconds (whichever comes first).
          registrationStrategy: 'registerWhenStable:30000'
        }),

        AtividadeLudicaModule,
        AtividadeConsultaModule,
        AtividadeExameModule,
        AtividadeMedicamentoModule,
        ConsultaModule,
        ConfiguracaoSistemaModule,
        EntradaSaidaModule,
        EspecialidadeModule,
        ExameModule,
        MedicamentoEstoqueModule,
        MedicamentoUsoModule,
        MovimentacaoEstoqueModule,
        ProfissionalModule,
        ResidenteModule,
        UsuarioModule,

        NaoEncontradoModule,
        LoginModule,
        HomeModule,

        HeaderModule,
        MenuItemsModule,
    ],
    providers: [
        MessageService,
        { provide: HTTP_INTERCEPTORS, useClass: AutenticacaoInterceptorService, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: DataHoraInterceptorService, multi: true },
        provideNgxMask()
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
