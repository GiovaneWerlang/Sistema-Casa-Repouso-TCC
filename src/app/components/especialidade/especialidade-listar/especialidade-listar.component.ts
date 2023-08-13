import { Component, OnInit } from '@angular/core';
import { Especialidade } from '../modelo/especialidade';
import { EspecialidadeService } from '../service/especialidade.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-especialidade-listar',
  templateUrl: './especialidade-listar.component.html',
  styleUrls: ['./especialidade-listar.component.css'],
})
export class EspecialidadeListarComponent implements OnInit {

  public items: Especialidade[] = [];

  constructor(
    private especialidadeService:EspecialidadeService,
    private router: Router
    ) {
      this.carregarLista();
    }

  novo(){
    this.router.navigate(['/especialidade/cadastrar']);
  }

  ngOnInit(): void {
  }

  delete(id:any){
    this.especialidadeService.delete(id).subscribe((res)=>{
      console.log(res);
      this.carregarLista();
    })
  }

  edit(id:number){
    this.router.navigate([`/especialidade/editar/${id}`]);
  }

  carregarLista():void{
    this.especialidadeService.list().subscribe((especialidade)=>{
      this.items = especialidade;
    })
  }
}
