import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicamentousoListarComponent } from './medicamentouso-listar.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { BlockUIModule } from 'primeng/blockui';
import { ButtonModule } from 'primeng/button';
import { PaginatorModule } from 'primeng/paginator';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { TableModule } from 'primeng/table';
import { MessageService } from 'primeng/api';
import { ConfirmDialogModule } from 'primeng/confirmdialog';

describe('MedicamentousoListarComponent', () => {
  let component: MedicamentousoListarComponent;
  let fixture: ComponentFixture<MedicamentousoListarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MedicamentousoListarComponent],
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
    fixture = TestBed.createComponent(MedicamentousoListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
