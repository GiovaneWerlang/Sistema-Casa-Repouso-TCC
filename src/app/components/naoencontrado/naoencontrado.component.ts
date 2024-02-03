import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-naoencontrado',
  templateUrl: './naoencontrado.component.html',
  styleUrls: ['./naoencontrado.component.css']
})
export class NaoencontradoComponent {

  constructor(private router: Router){}

  voltar(){
    this.router.navigate(['/home']);
  }
}
