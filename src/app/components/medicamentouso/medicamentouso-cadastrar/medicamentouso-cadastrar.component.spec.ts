import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicamentousoCadastrarComponent } from './medicamentouso-cadastrar.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { ToastModule } from 'primeng/toast';
import { TooltipModule } from 'primeng/tooltip';
import { CalendarModule } from 'primeng/calendar';

describe('MedicamentousoCadastrarComponent', () => {
  let component: MedicamentousoCadastrarComponent;
  let fixture: ComponentFixture<MedicamentousoCadastrarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MedicamentousoCadastrarComponent],
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
    fixture = TestBed.createComponent(MedicamentousoCadastrarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
