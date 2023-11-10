import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExameCadastrarComponent } from './exame-cadastrar.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { ToastModule } from 'primeng/toast';
import { TooltipModule } from 'primeng/tooltip';
import { CalendarModule } from 'primeng/calendar';

describe('ExameCadastrarComponent', () => {
  let component: ExameCadastrarComponent;
  let fixture: ComponentFixture<ExameCadastrarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ExameCadastrarComponent],
      imports: [
        RouterTestingModule, 
        HttpClientTestingModule,
        FormsModule,
        ReactiveFormsModule,
        DropdownModule,
        ButtonModule,
        ToastModule,
        TooltipModule,
        CalendarModule,
      ],
    });
    fixture = TestBed.createComponent(ExameCadastrarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
