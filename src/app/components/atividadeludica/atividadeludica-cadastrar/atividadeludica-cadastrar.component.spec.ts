import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtividadeludicaCadastrarComponent } from './atividadeludica-cadastrar.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { CalendarModule } from 'primeng/calendar';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { ToastModule } from 'primeng/toast';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

describe('AtividadeludicaCadastrarComponent', () => {
  let component: AtividadeludicaCadastrarComponent;
  let fixture: ComponentFixture<AtividadeludicaCadastrarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        AtividadeludicaCadastrarComponent,
      ],
      imports: [
        RouterTestingModule, 
        HttpClientTestingModule, 
        CalendarModule, 
        ButtonModule,
        DropdownModule,
        ToastModule,
        FormsModule,
        ReactiveFormsModule
      ],
    });
    fixture = TestBed.createComponent(AtividadeludicaCadastrarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
