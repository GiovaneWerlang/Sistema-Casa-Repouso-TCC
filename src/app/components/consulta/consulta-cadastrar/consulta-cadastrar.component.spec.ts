import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaCadastrarComponent } from './consulta-cadastrar.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { TooltipModule } from 'primeng/tooltip';
import { CalendarModule } from 'primeng/calendar';
import { MessageService } from 'primeng/api';

describe('ConsultaCadastrarComponent', () => {
  let component: ConsultaCadastrarComponent;
  let fixture: ComponentFixture<ConsultaCadastrarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConsultaCadastrarComponent],
      imports: [
        RouterTestingModule, 
        HttpClientTestingModule,
        FormsModule,
        ReactiveFormsModule,
        DropdownModule,
        ButtonModule,
        TooltipModule,
        CalendarModule,
      ],
      providers: [MessageService]
    });
    fixture = TestBed.createComponent(ConsultaCadastrarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
