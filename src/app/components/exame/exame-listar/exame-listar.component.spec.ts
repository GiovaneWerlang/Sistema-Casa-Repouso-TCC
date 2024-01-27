import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExameListarComponent } from './exame-listar.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { BlockUIModule } from 'primeng/blockui';
import { ButtonModule } from 'primeng/button';
import { PaginatorModule } from 'primeng/paginator';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { TableModule } from 'primeng/table';
import { MessageService } from 'primeng/api';

describe('ExameListarComponent', () => {
  let component: ExameListarComponent;
  let fixture: ComponentFixture<ExameListarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ExameListarComponent],
      imports: [
        RouterTestingModule, 
        HttpClientTestingModule, 
        TableModule, 
        ButtonModule, 
        PaginatorModule, 
        BlockUIModule,
        ProgressSpinnerModule,
      ],
      providers: [MessageService]
    });
    fixture = TestBed.createComponent(ExameListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
