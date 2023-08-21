import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { CrudService } from '../../crud-service';

@Component({
  selector: 'app-crud-table',
  templateUrl: './crud-table.component.html',
  styleUrls: ['./crud-table.component.css']
})
export class CrudTableComponent<T> {
  @Input() cols:string[] = [];
  public items: T[] = [];

  constructor(
    private service:CrudService<T>,
    private router: Router
    ) {
      this.carregarLista();
    }

  novo(){
    this.router.navigate([`/${this.service.getUrl()}/cadastrar`]);
  }

  ngOnInit(): void {
  }

  delete(id:any){
    this.service.delete(id).subscribe((res)=>{
      this.carregarLista();
    })
  }

  edit(id:number){
    this.router.navigate([`/${this.service.getUrl()}/editar/${id}`]);
  }

  carregarLista():void{
    this.service.list().subscribe((item)=>{
      this.items = item;
    })
  }
}
