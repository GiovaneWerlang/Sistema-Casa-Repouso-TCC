import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaListarComponent } from './consulta-listar.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { BlockUIModule } from 'primeng/blockui';
import { ButtonModule } from 'primeng/button';
import { PaginatorModule } from 'primeng/paginator';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { TableModule } from 'primeng/table';
import { ToastModule } from 'primeng/toast';

describe('ConsultaListarComponent', () => {
  let component: ConsultaListarComponent;
  let fixture: ComponentFixture<ConsultaListarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConsultaListarComponent],
      imports: [
        RouterTestingModule, 
        HttpClientTestingModule, 
        TableModule, 
        ButtonModule, 
        PaginatorModule, 
        BlockUIModule,
        ProgressSpinnerModule,
        ToastModule,
      ],
    });
    fixture = TestBed.createComponent(ConsultaListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
