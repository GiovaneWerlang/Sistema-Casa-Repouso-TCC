import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtividademedicamentoEditarComponent } from './atividademedicamento-editar.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { MessageService } from 'primeng/api';
import { CalendarModule } from 'primeng/calendar';
import { DropdownModule } from 'primeng/dropdown';
import { ReactiveFormsModule } from '@angular/forms';

describe('AtividademedicamentoEditarComponent', () => {
  let component: AtividademedicamentoEditarComponent;
  let fixture: ComponentFixture<AtividademedicamentoEditarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AtividademedicamentoEditarComponent],
      imports: [ReactiveFormsModule, HttpClientTestingModule, RouterTestingModule, CalendarModule, DropdownModule],
      providers:[MessageService]
    });
    fixture = TestBed.createComponent(AtividademedicamentoEditarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
