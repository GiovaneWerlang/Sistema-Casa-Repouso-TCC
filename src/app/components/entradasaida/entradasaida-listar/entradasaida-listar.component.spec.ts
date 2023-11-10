import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntradasaidaListarComponent } from './entradasaida-listar.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { BlockUIModule } from 'primeng/blockui';
import { ButtonModule } from 'primeng/button';
import { PaginatorModule } from 'primeng/paginator';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { TableModule } from 'primeng/table';
import { ToastModule } from 'primeng/toast';

describe('EntradasaidaListarComponent', () => {
  let component: EntradasaidaListarComponent;
  let fixture: ComponentFixture<EntradasaidaListarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EntradasaidaListarComponent],
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
    fixture = TestBed.createComponent(EntradasaidaListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
