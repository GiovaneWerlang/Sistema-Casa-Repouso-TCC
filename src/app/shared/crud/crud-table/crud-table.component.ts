import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { CrudService } from '../crud-service/crud-service';
import { LabelValue } from '../../labelvalue/labelvalue';
import { ToastService } from '../../toast-service/toast.service';
import { ConfirmationService } from 'primeng/api';

@Component({
  selector: 'app-crud-table',
  templateUrl: './crud-table.component.html',
  styleUrls: ['./crud-table.component.css'],
  providers: [ConfirmationService]
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
    private toastService: ToastService,
    private confirmationService: ConfirmationService
  ) {
  }

  novo() {
    this.router.navigate([`/${this.service.getUrl()}/cadastrar`]);
  }

  ngOnInit(): void {
  }

  delete(id: any) {
    this.confirmationService.confirm({
      message: 'Tem certeza que deseja deletar?',
      header: 'Confirmação',
      icon: 'pi pi-info-circle',
      acceptButtonStyleClass: "p-button-danger p-button-text",
      rejectButtonStyleClass: "p-button-text p-button-text",
      acceptIcon: "none",
      rejectIcon: "none",
      accept: () => {
        this.service.delete(id).subscribe((res: any) => {
          this.carregarLista(0, this.rows);
        })
      },
      reject: () => {
      }
    });
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
      error: (error: any) => {
        this.carregando = false;
        this.total = 0;
        this.items = [];
        this.toastService.toastBase('warn', 'Não foi possível carregar!', error );
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
        error: (error:any) => {
          this.carregando = false;
          this.toastService.toastBase('warn', 'Não foi possível carregar!', error );
        }
      })
    }
  }

}
