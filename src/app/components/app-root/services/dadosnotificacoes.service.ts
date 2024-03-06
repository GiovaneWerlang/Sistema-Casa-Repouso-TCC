import { Injectable, OnDestroy } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DadosnotificacoesService implements OnDestroy {

  private receberNotificacoes$: Subject<boolean>;

  constructor() {
    this.receberNotificacoes$ = new Subject();
  }

  setReceberNotificacoes(receber : boolean){
    this.receberNotificacoes$.next(receber);
  }

  getReceberNotificacoes(){
    return this.receberNotificacoes$.asObservable();
  }

  ngOnDestroy(): void {
      this.receberNotificacoes$.unsubscribe();
  }
}
