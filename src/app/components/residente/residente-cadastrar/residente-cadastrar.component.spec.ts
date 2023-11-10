import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResidenteCadastrarComponent } from './residente-cadastrar.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { ToastModule } from 'primeng/toast';
import { TooltipModule } from 'primeng/tooltip';
import { CalendarModule } from 'primeng/calendar';
import { InputMaskModule } from 'primeng/inputmask';

describe('ResidenteCadastrarComponent', () => {
  let component: ResidenteCadastrarComponent;
  let fixture: ComponentFixture<ResidenteCadastrarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ResidenteCadastrarComponent],
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
    fixture = TestBed.createComponent(ResidenteCadastrarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
