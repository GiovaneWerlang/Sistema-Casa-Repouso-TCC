import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfissionalCadastrarComponent } from './profissional-cadastrar.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { DropdownModule } from 'primeng/dropdown';
import { InputMaskModule } from 'primeng/inputmask';
import { ToastModule } from 'primeng/toast';
import { TooltipModule } from 'primeng/tooltip';

describe('ProfissionalCadastrarComponent', () => {
  let component: ProfissionalCadastrarComponent;
  let fixture: ComponentFixture<ProfissionalCadastrarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProfissionalCadastrarComponent],
      imports: [
        RouterTestingModule, 
        HttpClientTestingModule,
        FormsModule,
        ReactiveFormsModule,
        DropdownModule,
        CalendarModule,
        ButtonModule,
        ToastModule,
        TooltipModule,
        InputMaskModule,
      ],
    });
    fixture = TestBed.createComponent(ProfissionalCadastrarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
