import { CrudService } from 'src/app/shared/crud-service/crud-service';
import { MovimentacaoestoqueService } from './../service/movimentacaoestoque.service';
import { Component } from '@angular/core';
import { MovimentacaoEstoque } from '../modelo/movimentacaoestoque';
import { Router } from '@angular/router';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { TipoMovimentacao } from 'src/app/shared/tipomovimentacao/tipomovimentacao';

@Component({
  selector: 'app-movimentacaoestoque-listar',
  templateUrl: './movimentacaoestoque-listar.component.html',
  styleUrls: ['./movimentacaoestoque-listar.component.css'],
  providers: [  { provide: CrudService, useExisting: MovimentacaoestoqueService}]
})
export class MovimentacaoestoqueListarComponent {
  cols:string[] = ["Id", "Quantidade", "Tipo","Medicamento"];
  public items: MovimentacaoEstoque[] = [];

  tipos:LabelValue[] = TipoMovimentacao;

  first:number = 0;
  rows:number = 10;
  total:number = 0;

  page: number = 0;
  sort: string = "id";
  asc: boolean = true;
  
  constructor(
    private service:CrudService<MovimentacaoEstoque>,
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
    this.service.pagesort(page, size, this.sort, this.asc).subscribe((page: any) => {
      this.items = page.lista;
      this.total = page.total;
    })
  }

  onPageChange(event:any){
    this.rows = event.rows;
    this.carregarLista(event.page, event.rows);
  }

  customSort(event: any) {
    if (event) {
      this.sort = event.sortField ? event.sortField : "id";
      this.asc = event.sortOrder === 1;
      this.service.pagesort(this.page, this.rows, this.sort , this.asc).subscribe((page: any) => {
        this.items = page.lista;
        this.total = page.total;
      })
    }
  }
}
