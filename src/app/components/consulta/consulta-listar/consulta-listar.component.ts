import { Component } from '@angular/core';
import { ConsultaService } from '../service/consulta.service';
import { CrudService } from 'src/app/shared/crud-service/crud-service';
import { Consulta } from '../modelo/consulta';
import { Router } from '@angular/router';

@Component({
  selector: 'app-consulta-listar',
  templateUrl: './consulta-listar.component.html',
  styleUrls: ['./consulta-listar.component.css'],
  providers: [  { provide: CrudService, useExisting: ConsultaService}]
})
export class ConsultaListarComponent {
  cols:string[] = ["Id", "Descrição", "DataHora","Local", "Residente"];
  public items: Consulta[] = [];

  first:number = 0;
  rows:number = 10;
  total:number = 0;

  constructor(
    private service:CrudService<Consulta>,
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
