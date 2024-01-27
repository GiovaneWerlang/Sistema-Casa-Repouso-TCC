import { Component } from '@angular/core';
import { ProfissionalService } from '../service/profissional.service';
import { CrudService } from 'src/app/shared/crud-service/crud-service';
import { Profissional } from '../modelo/profissional';
import { Router } from '@angular/router';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { Situacoes } from 'src/app/shared/situacoes/situacoes';
import { Funcoes } from 'src/app/shared/funcoes/funcoes';
import { ToastService } from 'src/app/shared/toast-service/toast.service';

@Component({
  selector: 'app-profissional-listar',
  templateUrl: './profissional-listar.component.html',
  styleUrls: ['./profissional-listar.component.css'],
  providers: [{ provide: CrudService, useExisting: ProfissionalService }]
})
export class ProfissionalListarComponent {
  cols: string[] = ["Id", "Nome", "CPF", "Função", "Situação"];
  public items: Profissional[] = [];

  first: number = 0;
  rows: number = 10;
  total: number = 0;

  page: number = 0;
  sort: string = "id";
  asc: boolean = true;

  situacoes: LabelValue[] = Situacoes;
  funcoes: LabelValue[] = Funcoes;

  carregando: boolean = false;

  constructor(
    private service: CrudService<Profissional>,
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
          this.carregando = false
          this.toastService.toastBase('warn', 'Não foi possível carregar!', error);
        }
      })
    }
  }
}
