import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtividadeexameListarComponent } from './atividadeexame-listar.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MessageService } from 'primeng/api';
import { RouterTestingModule } from '@angular/router/testing';
import { BlockUIModule } from 'primeng/blockui';
import { CalendarModule } from 'primeng/calendar';
import { PaginatorModule } from 'primeng/paginator';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { TableModule } from 'primeng/table';

describe('AtividadeexameListarComponent', () => {
  let component: AtividadeexameListarComponent;
  let fixture: ComponentFixture<AtividadeexameListarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AtividadeexameListarComponent],
      imports: [HttpClientTestingModule, RouterTestingModule, TableModule, PaginatorModule, BlockUIModule, CalendarModule, ProgressSpinnerModule],
      providers:[MessageService]
    });
    fixture = TestBed.createComponent(AtividadeexameListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
