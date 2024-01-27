import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntradasaidaCadastrarComponent } from './entradasaida-cadastrar.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { TooltipModule } from 'primeng/tooltip';
import { CalendarModule } from 'primeng/calendar';
import { MessageService } from 'primeng/api';

describe('EntradasaidaCadastrarComponent', () => {
  let component: EntradasaidaCadastrarComponent;
  let fixture: ComponentFixture<EntradasaidaCadastrarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EntradasaidaCadastrarComponent],
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
    fixture = TestBed.createComponent(EntradasaidaCadastrarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
