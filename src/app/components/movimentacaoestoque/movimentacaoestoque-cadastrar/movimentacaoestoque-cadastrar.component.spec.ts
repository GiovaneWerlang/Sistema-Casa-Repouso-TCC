import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovimentacaoestoqueCadastrarComponent } from './movimentacaoestoque-cadastrar.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { TooltipModule } from 'primeng/tooltip';
import { MessageService } from 'primeng/api';

describe('MovimentacaoestoqueCadastrarComponent', () => {
  let component: MovimentacaoestoqueCadastrarComponent;
  let fixture: ComponentFixture<MovimentacaoestoqueCadastrarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MovimentacaoestoqueCadastrarComponent],
      imports: [
        RouterTestingModule, 
        HttpClientTestingModule,
        FormsModule,
        ReactiveFormsModule,
        DropdownModule,
        ButtonModule,
        TooltipModule,
      ],
      providers: [MessageService]
    });
    fixture = TestBed.createComponent(MovimentacaoestoqueCadastrarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
