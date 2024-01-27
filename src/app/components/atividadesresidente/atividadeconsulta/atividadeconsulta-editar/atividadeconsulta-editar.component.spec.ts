import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtividadeconsultaEditarComponent } from './atividadeconsulta-editar.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { MessageService } from 'primeng/api';
import { CalendarModule } from 'primeng/calendar';
import { DropdownModule } from 'primeng/dropdown';
import { ReactiveFormsModule } from '@angular/forms';

describe('AtividadeconsultaEditarComponent', () => {
  let component: AtividadeconsultaEditarComponent;
  let fixture: ComponentFixture<AtividadeconsultaEditarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AtividadeconsultaEditarComponent],
      imports: [ReactiveFormsModule, HttpClientTestingModule, RouterTestingModule, CalendarModule, DropdownModule, ],
      providers:[MessageService]
    });
    fixture = TestBed.createComponent(AtividadeconsultaEditarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
