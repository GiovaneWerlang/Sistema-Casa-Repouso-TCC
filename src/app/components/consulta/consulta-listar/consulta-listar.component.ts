import { Component } from '@angular/core';
import { ConsultaService } from '../service/consulta.service';
import { CrudService } from 'src/app/shared/crud/crud-service/crud-service';
import { Consulta } from '../modelo/consulta';
import { Router } from '@angular/router';
import { ConfirmationService, MessageService } from 'primeng/api';
import { ToastService } from 'src/app/shared/toast-service/toast.service';

@Component({
  selector: 'app-consulta-listar',
  templateUrl: './consulta-listar.component.html',
  styleUrls: ['./consulta-listar.component.css'],
  providers: [{ provide: CrudService, useExisting: ConsultaService }, ConfirmationService]
})
export class ConsultaListarComponent {
  cols: string[] = ["Id", "Descrição", "DataHora", "Local", "Residente"];
  public items: Consulta[] = [];

  first: number = 0;
  rows: number = 10;
  total: number = 0;

  page: number = 0;
  sort: string = "id";
  asc: boolean = true;

  carregando: boolean = false;

  constructor(
    private service: CrudService<Consulta>,
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
      error: (error) => {
        this.carregando = false;
        this.total = 0;
        this.items = [];
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
