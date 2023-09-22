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
