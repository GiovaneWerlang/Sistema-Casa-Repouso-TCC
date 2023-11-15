import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { CrudService } from '../../crud-service/crud-service';
import { LabelValue } from '../../labelvalue/labelvalue';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-crud-table',
  templateUrl: './crud-table.component.html',
  styleUrls: ['./crud-table.component.css'],
  providers: [MessageService]
})
export class CrudTableComponent<T> {
  cols: LabelValue[] = [];
  @Input() set colunas(colunas: LabelValue[]) {
    this.cols = colunas;
    this.colspan = (colunas.length + 1).toString();
  }
  public items: T[] = [];

  first: number = 0;
  rows: number = 10;
  total: number = 0;

  page: number = 0;
  sort: string = "id";
  asc: boolean = true;

  carregando: boolean = false;
  colspan: string = '1';

  constructor(
    private service: CrudService<T>,
    private router: Router,
    private messageService: MessageService
  ) {
  }

  novo() {
    this.router.navigate([`/${this.service.getUrl()}/cadastrar`]);
  }

  ngOnInit(): void {
  }

  delete(id: any) {
    this.service.delete(id).subscribe((res) => {
      this.carregarLista(0, this.rows);
    })
  }

  edit(id: number) {
    this.router.navigate([`/${this.service.getUrl()}/editar/${id}`]);
  }

  carregarLista(page: number, size: number): void {
    this.carregando = true;
    this.service.pagesort(page, size, this.sort, this.asc).subscribe({
      next: (page: any) => {
        this.items = page.lista;
        this.total = page.total;
        this.carregando = false;
      },
      error: (error) => {
        this.carregando = false;
        this.messageService.add({ severity: 'warn', summary: 'Não foi possível carregar!', detail: error });
      }
    })
  }

  onPageChange(event: any) {
    this.rows = event.rows;
    this.carregarLista(event.page, event.rows);
  }

  customSort(event: any) {
    if (event) {
      this.carregando = true;
      this.sort = event.sortField ? event.sortField : "id";
      this.asc = event.sortOrder === 1;
      this.service.pagesort(this.page, this.rows, this.sort, this.asc).subscribe({
        next: (page: any) => {
          this.items = page.lista;
          this.total = page.total;
          this.carregando = false;
        },
        error: (error) => {
          this.carregando = false;
          this.messageService.add({ severity: 'warn', summary: 'Não foi possível carregar!', detail: error });
        }
      })
    }
  }

}
