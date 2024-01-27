import { Component } from '@angular/core';
import { MedicamentousoService } from '../service/medicamentouso.service';
import { MedicamentoUso } from '../modelo/medicamentouso';
import { CrudService } from 'src/app/shared/crud-service/crud-service';
import { Router } from '@angular/router';
import { ToastService } from 'src/app/shared/toast-service/toast.service';

@Component({
  selector: 'app-medicamentouso-listar',
  templateUrl: './medicamentouso-listar.component.html',
  styleUrls: ['./medicamentouso-listar.component.css'],
  providers: [{ provide: CrudService, useExisting: MedicamentousoService }]
})
export class MedicamentousoListarComponent {
  cols: string[] = ["Id", "DataHora Início", "Residente", "Medicamento"];
  public items: MedicamentoUso[] = [];

  first: number = 0;
  rows: number = 10;
  total: number = 0;

  page: number = 0;
  sort: string = "id";
  asc: boolean = true;

  carregando: boolean = false;

  constructor(
    private service: CrudService<MedicamentoUso>,
    private router: Router,
    private toastService: ToastService
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
        this.toastService.toastBase('warn', 'Não foi possível carregar!', error);
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
          this.toastService.toastBase('warn', 'Não foi possível carregar!', error);
        }
      })
    }
  }
}
