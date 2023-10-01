import { Component } from '@angular/core';
import { ExameService } from '../service/exame.service';
import { CrudService } from 'src/app/shared/crud-service/crud-service';
import { Exame } from '../modelo/exame';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { Router } from '@angular/router';

@Component({
  selector: 'app-exame-listar',
  templateUrl: './exame-listar.component.html',
  styleUrls: ['./exame-listar.component.css'],
  providers: [  { provide: CrudService, useExisting: ExameService}]
})
export class ExameListarComponent {
  cols:string[] = ["Id", "Nome", "DataHora","Local", "Residente"];
  public items: Exame[] = [];

  first:number = 0;
  rows:number = 10;
  total:number = 0;

  constructor(
    private service:CrudService<Exame>,
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
