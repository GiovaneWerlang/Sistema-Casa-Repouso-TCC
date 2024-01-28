import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtividademedicamentoListarComponent } from './atividademedicamento-listar.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MessageService } from 'primeng/api';
import { RouterTestingModule } from '@angular/router/testing';
import { BlockUIModule } from 'primeng/blockui';
import { CalendarModule } from 'primeng/calendar';
import { PaginatorModule } from 'primeng/paginator';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { TableModule } from 'primeng/table';
import { ConfirmDialogModule } from 'primeng/confirmdialog';

describe('AtividademedicamentoListarComponent', () => {
  let component: AtividademedicamentoListarComponent;
  let fixture: ComponentFixture<AtividademedicamentoListarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AtividademedicamentoListarComponent],
      imports: [
        HttpClientTestingModule, 
        RouterTestingModule, 
        TableModule, 
        PaginatorModule, 
        BlockUIModule, 
        CalendarModule, 
        ProgressSpinnerModule,
        ConfirmDialogModule,
      ],
      providers:[MessageService]
    });
    fixture = TestBed.createComponent(AtividademedicamentoListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
