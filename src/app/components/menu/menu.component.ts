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
      url: 'home'
    }, {
      label: 'Atividades Lúdicas',
      icon: 'pi pi-th-large',
      url: 'atividadeludica/listar'
    }, {
      label: 'Consulta',
      icon: 'pi pi-file',
      url: 'consulta/listar'
    }, {
      label: 'Entradas e Saídas',
      icon: 'pi pi-arrow-right-arrow-left',
      url: 'entradasaida/listar'
    }, {
      label: 'Especialidade',
      icon: 'pi pi-link',
      url: 'especialidade/listar'
    }, {
      label: 'Exame',
      icon: 'pi pi-clone',
      url: 'exame/listar'
    }, {
      label: 'Medicamento',
      icon: 'pi pi-box',
      url: 'medicamentoestoque/listar'
    }, {
      label: 'Medicamento em Uso',
      icon: 'pi pi-inbox',
      url: 'medicamentouso/listar'
    }, {
      label: 'Movimentação de Medicamento',
      icon: 'pi pi-tags',
      url: 'movimentacaoestoque/listar'
    }, {
      label: 'Profissional',
      icon: 'pi pi-briefcase',
      url: 'profissional/listar'
    }, {
      label: 'Residente',
      icon: 'pi pi-user',
      url: 'residente/listar'
    }, {
      label: 'Usuário',
      icon: 'pi pi-users',
      url: 'usuario/listar'
    }
  ];
}
