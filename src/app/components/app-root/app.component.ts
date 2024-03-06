import { MensagemSubscription } from './../atividadesresidente/notificacoes/modelo/mensagemSubscription';
import { NotificacoesService } from './../atividadesresidente/notificacoes/service/notificacoes.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { BreakpointserviceService } from './services/breakpointservice.service';
import { BreakpointState, Breakpoints } from '@angular/cdk/layout';
import { PrimeNGConfig } from 'primeng/api';
import { Translate } from 'src/app/shared/translate/translate';
import { AutenticacaoService } from '../login/service/autenticacao.service';
import { Subscription, interval } from 'rxjs';
import { SwPush } from '@angular/service-worker';
import { MensagemSubscriptionService } from '../atividadesresidente/notificacoes/service/mensagemsubscription.service';
import { Router } from '@angular/router';
import { DadosnotificacoesService } from './services/dadosnotificacoes.service';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
    title = 'client';
    desktop: boolean = true;
    autenticado: boolean = false;

    breakPointSubscription: Subscription = new Subscription;
    autenticacaoSubscription: Subscription = new Subscription;

    private readonly publicKey: string = 'BPtfmbAFQwggTeQKEH5pGLcP9qAC7ATbpvbxKTaIPaHVMhIoM4U_z-e-gDxIHiSUcEd41SVu-kq99frpGSTbBo0';

    swPushSubscription: Subscription = new Subscription;

    mensagemSwPushSubscription: Subscription = new Subscription;
    notificacaoSwPushSubscription: Subscription = new Subscription;


    pushSubscription: Subscription = new Subscription;

    inscricao: MensagemSubscription | undefined;

    receberSubscription: Subscription = new Subscription;

    constructor(
        private config: PrimeNGConfig,
        breakpointService: BreakpointserviceService,
        private _autenticacaoService: AutenticacaoService,
        private swPush: SwPush,
        private notificacoesService: NotificacoesService,
        private mensagemSubscriptionService: MensagemSubscriptionService,
        private _router: Router,
        private dadosnotificacoesService:DadosnotificacoesService
        ) {
        config.setTranslation(Translate);
        this.monitoraBreakspoints(breakpointService);
        this.monitoraAutenticado();

        this.monitoraRecebeNotificacoes();

        //this.requisitarPermissao();
        this.emitirMensagens();
        this.emitirNotificacaoClick();
    }

    ngOnInit(): void {
        this._autenticacaoService.autoLogin();
        const source = interval(1000 * 60);
        const subscribe = source.subscribe(val => {
            console.log(val);//pode usar a inscricao para buscar e receber apenas para si
            this.notificacoesService.notificarTodos().subscribe((not) => console.log(not));
        });
    }

    monitoraBreakspoints(breakpointService: BreakpointserviceService) {
        this.breakPointSubscription = breakpointService.getBreakpoints().subscribe((breakpoint: BreakpointState) => {
            const breakpoints = breakpoint.breakpoints;
            this.desktop = breakpoints[Breakpoints.Web] || breakpoints[Breakpoints.WebLandscape];
        });
    }

    monitoraAutenticado() {
        this.autenticacaoSubscription = this._autenticacaoService.dadoUsuario.asObservable().subscribe(
            dado => {
                this.autenticado = !!dado;
            }
        );
    }

    monitoraRecebeNotificacoes(){
        this.receberSubscription = this.dadosnotificacoesService.getReceberNotificacoes().subscribe((receber) => {
            console.log('receber', receber);
            if(receber){
                this.requisitarPermissao();
            } else {
                this.cancelarInscricao();
            }     
        })
    }

    emitirMensagens() {
        this.mensagemSwPushSubscription = this.swPush.messages.subscribe((message) => {
            console.log('mensagem', message)
        });
    }

    emitirNotificacaoClick() {
        this.notificacaoSwPushSubscription = this.swPush.notificationClicks.subscribe(({ action, notification }) => {
            console.log('acao', action)
            console.log('notificacao', notification);
            console.log('notificacao', notification.data.url);

            window.open(notification.data.url);
        });
    }

    requisitarPermissao() {
        if (!this.swPush.isEnabled) {
            console.log('Service worker não habilitado.');
            return;
        }

        this.swPush
            .requestSubscription({
                serverPublicKey: this.publicKey,
            })
            .then((sub) => {
                const pushSubscription = JSON.parse(JSON.stringify(sub));

                let mensagemSubscription: MensagemSubscription = {
                    endpoint: pushSubscription.endpoint,
                    p256dh: pushSubscription.keys.p256dh,
                    auth: pushSubscription.keys.auth
                }
                this.inscricao = mensagemSubscription;

                this.mensagemSubscriptionService.subscribe(mensagemSubscription).subscribe((res) => {
                    console.log(res);
                })
            })
            .catch((err) => console.log('erro', err));
    }

    cancelarInscricao() {
        this.swPush.unsubscribe().catch((reason:any) => console.log('razao', reason));
        if (this.inscricao)
            this.mensagemSubscriptionService.unsubscribe(this.inscricao).subscribe((res) => {
                console.log(res);
            });
    }

    ngOnDestroy(): void {
        this.breakPointSubscription.unsubscribe();
        this.autenticacaoSubscription.unsubscribe();

        this.swPushSubscription.unsubscribe();

        this.mensagemSwPushSubscription.unsubscribe();
        this.notificacaoSwPushSubscription.unsubscribe();
        this.receberSubscription.unsubscribe();
    }
}
