import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtividadeconsultaListarComponent } from './atividadeconsulta-listar.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MessageService } from 'primeng/api';
import { RouterTestingModule } from '@angular/router/testing';
import { TableModule } from 'primeng/table';
import { PaginatorModule } from 'primeng/paginator';
import { BlockUIModule } from 'primeng/blockui';
import { CalendarModule } from 'primeng/calendar';
import { ProgressSpinnerModule } from 'primeng/progressspinner';

describe('AtividadeconsultaListarComponent', () => {
  let component: AtividadeconsultaListarComponent;
  let fixture: ComponentFixture<AtividadeconsultaListarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AtividadeconsultaListarComponent],
      imports: [HttpClientTestingModule, RouterTestingModule, TableModule, PaginatorModule, BlockUIModule, CalendarModule, ProgressSpinnerModule],
      providers:[MessageService]
    });
    fixture = TestBed.createComponent(AtividadeconsultaListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
