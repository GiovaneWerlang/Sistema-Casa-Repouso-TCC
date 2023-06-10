import { Component, OnInit } from '@angular/core';
import { Especialidade } from '../modelo/especialidade';
import { EspecialidadeService } from '../service/especialidade.service';

@Component({
  selector: 'app-especialidade-listar',
  templateUrl: './especialidade-listar.component.html',
  styleUrls: ['./especialidade-listar.component.css']
})
export class EspecialidadeListarComponent implements OnInit {

  public especialidade: Especialidade[] = [];

  constructor(private especialidadeService:EspecialidadeService) { }

  ngOnInit(): void {
    this.carregarLista();
  }

  delete(id:any){
    this.especialidadeService.delete(id).subscribe((res)=>{
      console.log(res);
      this.carregarLista();
    })
  }


  carregarLista():void{
    console.log('teste');
    this.especialidadeService.list().subscribe((especialidade)=>{
      this.especialidade = especialidade;
    })
  }
}
