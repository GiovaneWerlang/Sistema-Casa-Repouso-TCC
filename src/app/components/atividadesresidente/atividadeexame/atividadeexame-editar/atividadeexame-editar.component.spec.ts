import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtividadeexameEditarComponent } from './atividadeexame-editar.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { MessageService } from 'primeng/api';
import { CalendarModule } from 'primeng/calendar';
import { DropdownModule } from 'primeng/dropdown';
import { ReactiveFormsModule } from '@angular/forms';

describe('AtividadeexameEditarComponent', () => {
  let component: AtividadeexameEditarComponent;
  let fixture: ComponentFixture<AtividadeexameEditarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AtividadeexameEditarComponent],
      imports: [ReactiveFormsModule, HttpClientTestingModule, RouterTestingModule, CalendarModule, DropdownModule],
      providers:[MessageService]
    });
    fixture = TestBed.createComponent(AtividadeexameEditarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
