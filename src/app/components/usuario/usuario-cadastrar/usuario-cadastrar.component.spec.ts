import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioCadastrarComponent } from './usuario-cadastrar.component';
import { RouterTestingModule } from "@angular/router/testing";
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { UsuarioService } from '../service/usuario.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DropdownModule } from 'primeng/dropdown';
import { ButtonModule } from 'primeng/button';
import { ToastModule } from 'primeng/toast';
import { TooltipModule } from 'primeng/tooltip';

describe('UsuarioCadastrarComponent', () => {
  let component: UsuarioCadastrarComponent;
  let fixture: ComponentFixture<UsuarioCadastrarComponent>;
  let userServiceStub: Partial<UsuarioService>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UsuarioCadastrarComponent],
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
      providers: [{ provide: UsuarioService, useValue: userServiceStub }]
    });
    fixture = TestBed.createComponent(UsuarioCadastrarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
