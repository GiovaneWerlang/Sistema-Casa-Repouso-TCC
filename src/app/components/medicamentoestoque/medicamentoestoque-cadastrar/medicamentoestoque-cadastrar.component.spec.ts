import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicamentoestoqueCadastrarComponent } from './medicamentoestoque-cadastrar.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { ToastModule } from 'primeng/toast';
import { TooltipModule } from 'primeng/tooltip';

describe('MedicamentoestoqueCadastrarComponent', () => {
  let component: MedicamentoestoqueCadastrarComponent;
  let fixture: ComponentFixture<MedicamentoestoqueCadastrarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MedicamentoestoqueCadastrarComponent],
      imports: [
        RouterTestingModule, 
        HttpClientTestingModule,
        FormsModule,
        ReactiveFormsModule,
        DropdownModule,
        ButtonModule,
        ToastModule,
        TooltipModule,
      ],
    });
    fixture = TestBed.createComponent(MedicamentoestoqueCadastrarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
