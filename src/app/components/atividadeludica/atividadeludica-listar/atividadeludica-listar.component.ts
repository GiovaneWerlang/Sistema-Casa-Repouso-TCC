import { Component } from '@angular/core';
import { AtividadeLudica } from '../modelo/atividadeludica';
import { AtividadeLudicaService } from '../service/atividadeludica.service';
import { CrudService } from 'src/app/shared/crud-service';
import { Router } from '@angular/router';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { Situacoes } from 'src/app/shared/situacoes/situacoes';

@Component({
  selector: 'app-atividadeludica',
  templateUrl: './atividadeludica-listar.component.html',
  styleUrls: ['./atividadeludica-listar.component.css'],
  providers: [  { provide: CrudService, useExisting: AtividadeLudicaService}]
})
export class AtividadeludicaListarComponent {

  public items: AtividadeLudica[] = [];

  first:number = 0;
  rows:number = 10;
  total:number = 0;

  page:number = 0;

  situacoes:LabelValue[] = Situacoes;

  constructor(
    private service:CrudService<AtividadeLudica>,
    private router: Router
    ) {
      //this.carregarLista(this.first, this.rows);
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
    this.service.pagesort(page, size,"id",true).subscribe((page:any)=>{     
      this.items = page.lista;
      this.total = page.total;
    })
  }

  onPageChange(event:any){
    this.rows = event.rows;
    this.page = event.page;
    this.carregarLista(event.page, event.rows);
  }

  customSort(event:any){
    console.log(event)
    if(event){
    this.service.pagesort(this.page, this.rows, event.sortField ? event.sortField : "id", event.sortOrder === 1).subscribe((page:any)=>{     
      this.items = page.lista;
      this.total = page.total;
    })
  }
  }
}
