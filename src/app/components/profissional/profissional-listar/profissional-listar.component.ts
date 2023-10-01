import { Component } from '@angular/core';
import { ProfissionalService } from '../service/profissional.service';
import { CrudService } from 'src/app/shared/crud-service/crud-service';
import { Profissional } from '../modelo/profissional';
import { Router } from '@angular/router';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { Situacoes } from 'src/app/shared/situacoes/situacoes';
import { Funcoes } from 'src/app/shared/funcoes/funcoes';

@Component({
  selector: 'app-profissional-listar',
  templateUrl: './profissional-listar.component.html',
  styleUrls: ['./profissional-listar.component.css'],
  providers: [  { provide: CrudService, useExisting: ProfissionalService}]
})
export class ProfissionalListarComponent {
  cols:string[] = ["Id", "Nome", "CPF","Função", "Situação"];
  public items: Profissional[] = [];

  first:number = 0;
  rows:number = 10;
  total:number = 0;

  situacoes:LabelValue[] = Situacoes;
  funcoes:LabelValue[] = Funcoes;

  constructor(
    private service:CrudService<Profissional>,
    private router: Router
    ) {
      this.carregarLista(this.first, this.rows);
    }

  novo(){
    this.router.navigate([`/${this.service.getUrl()}/cadastrar`]);
  }

  ngOnInit(): void {
  }

  delete(id:any){
    this.service.delete(id).subscribe((res)=>{
      this.carregarLista(0, this.rows);
    })
  }

  edit(id:number){
    this.router.navigate([`/${this.service.getUrl()}/editar/${id}`]);
  }

  carregarLista(page:number, size: number):void{
    this.service.page(page, size).subscribe((page:any)=>{     
      this.items = page.lista;
      this.total = page.total;
    })
  }

  onPageChange(event:any){
    this.rows = event.rows;
    this.carregarLista(event.page, event.rows);
  }
}
