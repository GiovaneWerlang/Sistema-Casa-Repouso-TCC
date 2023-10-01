import { Component } from '@angular/core';
import { EntradasaidaService } from '../service/entradasaida.service';
import { CrudService } from 'src/app/shared/crud-service/crud-service';
import { EntradaSaida } from '../modelo/entradasaida';
import { Router } from '@angular/router';
import { ResidenteService } from '../../residente/service/residente.service';

@Component({
  selector: 'app-entradasaida-listar',
  templateUrl: './entradasaida-listar.component.html',
  styleUrls: ['./entradasaida-listar.component.css'],
  providers: [  { provide: CrudService, useExisting: EntradasaidaService}]

})
export class EntradasaidaListarComponent {
  cols:string[] = ["Id", "DataHora Saída", "DataHora Entrada", "Residente"];
  public items: EntradaSaida[] = [];

  first:number = 0;
  rows:number = 10;
  total:number = 0;
  
  constructor(
    private service:CrudService<EntradaSaida>,
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
