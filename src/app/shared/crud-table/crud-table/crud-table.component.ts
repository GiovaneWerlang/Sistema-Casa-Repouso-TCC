import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { CrudService } from '../../crud-service/crud-service';
import { LabelValue } from '../../labelvalue/labelvalue';

@Component({
  selector: 'app-crud-table',
  templateUrl: './crud-table.component.html',
  styleUrls: ['./crud-table.component.css']
})
export class CrudTableComponent<T> {
  @Input() cols:LabelValue[] = [];
  public items: T[] = [];

  first:number = 0;
  rows:number = 10;
  total:number = 0;

  page: number = 0;
  sort: string = "id";
  asc: boolean = true;

  constructor(
    private service:CrudService<T>,
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
