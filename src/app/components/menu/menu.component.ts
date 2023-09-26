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
      icon: 'pi pi-link',
      url: 'atividadeludica/listar'
    }, {
      label: 'Consulta',
      icon: 'pi pi-link',
      url: 'consulta/listar'
    }, {
      label: 'Entradas e Saídas',
      icon: 'pi pi-link',
      url: 'entradasaida/listar'
    }, {
      label: 'Especialidade',
      icon: 'pi pi-link',
      url: 'especialidade/listar'
    }, {
      label: 'Exame',
      icon: 'pi pi-link',
      url: 'exame/listar'
    }, {
      label: 'Medicamento',
      icon: 'pi pi-link',
      url: 'medicamento/listar'
    }, {
      label: 'Profissional',
      icon: 'pi pi-link',
      url: 'profissional/listar'
    }, {
      label: 'Residente',
      icon: 'pi pi-link',
      url: 'residente/listar'
    }, {
      label: 'Usuário',
      icon: 'pi pi-link',
      url: 'usuario/listar'
    }
  ];
}
