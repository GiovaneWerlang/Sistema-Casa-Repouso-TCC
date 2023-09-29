import { Component } from '@angular/core';
import { AtividadeLudica } from '../modelo/atividadeludica';
import { AtividadeLudicaService } from '../service/atividadeludica.service';
import { CrudService } from 'src/app/shared/crud-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-atividadeludica',
  templateUrl: './atividadeludica-listar.component.html',
  styleUrls: ['./atividadeludica-listar.component.css'],
  providers: [  { provide: CrudService, useExisting: AtividadeLudicaService}]
})
export class AtividadeludicaListarComponent {
  cols:string[] = ["Id", "Nome", "DataHora","Situação"];
  public items: AtividadeLudica[] = [];

  first:number = 0;
  rows:number = 10;
  total:number = 0;

  constructor(
    private service:CrudService<AtividadeLudica>,
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
