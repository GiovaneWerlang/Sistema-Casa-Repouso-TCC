import { Component } from '@angular/core';
import { MedicamentousoService } from '../service/medicamentouso.service';
import { MedicamentoUso } from '../modelo/medicamentouso';
import { CrudService } from 'src/app/shared/crud-service/crud-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-medicamentouso-listar',
  templateUrl: './medicamentouso-listar.component.html',
  styleUrls: ['./medicamentouso-listar.component.css'],
  providers: [  { provide: CrudService, useExisting: MedicamentousoService}]
})
export class MedicamentousoListarComponent {
  cols:string[] = ["Id", "DataHora Início", "Residente", "Medicamento"];
  public items: MedicamentoUso[] = [];

  first:number = 0;
  rows:number = 10;
  total:number = 0;

  constructor(
    private service:CrudService<MedicamentoUso>,
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
