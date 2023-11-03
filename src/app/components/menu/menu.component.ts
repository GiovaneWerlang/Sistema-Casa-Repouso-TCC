import { Component } from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent {
  items = [
    {
      label: 'Home',
      icon: 'pi pi-home',
      routerLink: 'home'
    }, {
      label: 'Atividades Lúdicas',
      icon: 'pi pi-th-large',
      routerLink: 'atividadeludica/listar'
    }, {
      label: 'Consulta',
      icon: 'pi pi-file',
      routerLink: 'consulta/listar'
    }, {
      label: 'Entradas e Saídas',
      icon: 'pi pi-arrow-right-arrow-left',
      routerLink: 'entradasaida/listar'
    }, {
      label: 'Especialidade',
      icon: 'pi pi-link',
      routerLink: 'especialidade/listar'
    }, {
      label: 'Exame',
      icon: 'pi pi-clone',
      routerLink: 'exame/listar'
    }, {
      label: 'Medicamento',
      icon: 'pi pi-box',
      routerLink: 'medicamentoestoque/listar'
    }, {
      label: 'Medicamento em Uso',
      icon: 'pi pi-inbox',
      routerLink: 'medicamentouso/listar'
    }, {
      label: 'Movimentação de Medicamento',
      icon: 'pi pi-tags',
      routerLink: 'movimentacaoestoque/listar'
    }, {
      label: 'Profissional',
      icon: 'pi pi-briefcase',
      routerLink: 'profissional/listar'
    }, {
      label: 'Residente',
      icon: 'pi pi-user',
      routerLink: 'residente/listar'
    }, {
      label: 'Usuário',
      icon: 'pi pi-users',
      routerLink: 'usuario/listar'
    }
  ];
  a = '';
}
