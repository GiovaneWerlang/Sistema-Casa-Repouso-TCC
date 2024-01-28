import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovimentacaoestoqueListarComponent } from './movimentacaoestoque-listar.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { BlockUIModule } from 'primeng/blockui';
import { ButtonModule } from 'primeng/button';
import { PaginatorModule } from 'primeng/paginator';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { TableModule } from 'primeng/table';
import { MessageService } from 'primeng/api';
import { ConfirmDialogModule } from 'primeng/confirmdialog';

describe('MovimentacaoestoqueListarComponent', () => {
  let component: MovimentacaoestoqueListarComponent;
  let fixture: ComponentFixture<MovimentacaoestoqueListarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MovimentacaoestoqueListarComponent],
      imports: [
        RouterTestingModule, 
        HttpClientTestingModule, 
        TableModule, 
        ButtonModule, 
        PaginatorModule, 
        BlockUIModule,
        ProgressSpinnerModule,
        ConfirmDialogModule,
      ],
      providers: [MessageService]
    });
    fixture = TestBed.createComponent(MovimentacaoestoqueListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
