/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EspecialidadeCadastrarComponent } from './especialidade-cadastrar.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { TooltipModule } from 'primeng/tooltip';
import { MessageService } from 'primeng/api';

describe('EspecialidadeCadastrarComponent', () => {
  let component: EspecialidadeCadastrarComponent;
  let fixture: ComponentFixture<EspecialidadeCadastrarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EspecialidadeCadastrarComponent ],
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
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EspecialidadeCadastrarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
