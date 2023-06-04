import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment.development';

@Component({
  selector: 'app-especialidade-cadastrar',
  templateUrl: './especialidade-cadastrar.component.html',
  styleUrls: ['./especialidade-cadastrar.component.css']
})
export class EspecialidadeCadastrarComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    console.log(environment.apiUrl)
  }

}
