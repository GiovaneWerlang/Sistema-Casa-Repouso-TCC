import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfissionalCadastrarComponent } from './profissional-cadastrar.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { DropdownModule } from 'primeng/dropdown';
import { InputMaskModule } from 'primeng/inputmask';
import { TooltipModule } from 'primeng/tooltip';
import { MessageService } from 'primeng/api';

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
        TooltipModule,
        InputMaskModule,
      ],
      providers: [MessageService]
    });
    fixture = TestBed.createComponent(ProfissionalCadastrarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
