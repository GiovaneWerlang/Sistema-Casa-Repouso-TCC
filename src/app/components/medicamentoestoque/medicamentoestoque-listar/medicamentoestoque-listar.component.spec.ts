import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicamentoestoqueListarComponent } from './medicamentoestoque-listar.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { MessageService } from 'primeng/api';
import { BlockUIModule } from 'primeng/blockui';
import { ButtonModule } from 'primeng/button';
import { PaginatorModule } from 'primeng/paginator';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { TableModule } from 'primeng/table';
import { CrudTableComponent } from 'src/app/shared/crud/crud-table/crud-table.component';
import { ConfirmDialogModule } from 'primeng/confirmdialog';

describe('MedicamentoestoqueListarComponent', () => {
  let component: MedicamentoestoqueListarComponent;
  let fixture: ComponentFixture<MedicamentoestoqueListarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MedicamentoestoqueListarComponent, CrudTableComponent],
      imports: [
        RouterTestingModule, 
        HttpClientTestingModule, 
        TableModule, 
        ButtonModule, 
        PaginatorModule, 
        BlockUIModule,
        ProgressSpinnerModule,
        ConfirmDialogModule,
      ],
      providers: [MessageService]
    });
    fixture = TestBed.createComponent(MedicamentoestoqueListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
