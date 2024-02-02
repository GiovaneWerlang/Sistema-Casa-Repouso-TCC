import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ConfirmationService, MessageService } from 'primeng/api';
import { CrudService } from 'src/app/shared/crud/crud-service/crud-service';
import { AtividadeMedicamento } from '../modelo/atividademedicamento';
import { AtividadeMedicamentoService } from '../service/atividademedicamento.service';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { SituacaoAtividade } from 'src/app/shared/situacaoatividade/situacaoatividade';
import { ToastService } from 'src/app/shared/toast-service/toast.service';

@Component({
  selector: 'app-atividademedicamento-listar',
  templateUrl: './atividademedicamento-listar.component.html',
  styleUrls: ['./atividademedicamento-listar.component.css'],
  providers: [{ provide: CrudService, useExisting: AtividadeMedicamentoService }, ConfirmationService]
})
export class AtividademedicamentoListarComponent {
  cols: string[] = ["Id", "Descrição", "DataHora", "Situação"];
  public items: AtividadeMedicamento[] = [];

  situacoes: LabelValue[] = SituacaoAtividade;
  
  first: number = 0;
  rows: number = 10;
  total: number = 0;

  page: number = 0;
  sort: string = "id";
  asc: boolean = true;

  carregando: boolean = false;

  constructor(
    private service: CrudService<AtividadeMedicamento>,
    private router: Router,
    private toastService: ToastService,
    private confirmationService: ConfirmationService
  ) {
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
        this.service.delete(id).subscribe((res) => {
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
        this.toastService.toastBase('warn', 'Não foi possível carregar!', error);
      }
    })
  }

  onPageChange(event: any) {
    this.rows = event.rows;
    this.page = event.page;
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
        error: (error: any) => {
          this.carregando = false;
          this.toastService.toastBase('warn', 'Não foi possível carregar!', error);
        }
      })
    }
  }
}
