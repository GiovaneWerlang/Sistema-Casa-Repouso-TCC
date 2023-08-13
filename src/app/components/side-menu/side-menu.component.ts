import { Component } from '@angular/core';

@Component({
  selector: 'app-side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['./side-menu.component.css']
})
export class SideMenuComponent {
  sidebarVisible: boolean = false;

  items = [
    {
        label: 'New',
        icon: 'pi pi-fw pi-plus',
    },
    {
        label: 'Delete',
        icon: 'pi pi-fw pi-trash',
        command: () => {
          this.update();
      }
    },{
      label: 'Especialidade',
      icon: 'pi pi-link',
      url: 'especialidade/listar'
    }
];

  update(){
    console.log('update')
  }
}
