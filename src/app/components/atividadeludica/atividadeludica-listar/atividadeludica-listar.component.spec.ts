import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtividadeludicaListarComponent } from './atividadeludica-listar.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { PaginatorModule } from 'primeng/paginator';
import { BlockUIModule } from 'primeng/blockui';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { MessageService } from 'primeng/api';
import { ConfirmDialogModule } from 'primeng/confirmdialog';

describe('AtividadeludicaListarComponent', () => {
  let component: AtividadeludicaListarComponent;
  let fixture: ComponentFixture<AtividadeludicaListarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AtividadeludicaListarComponent],
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
    fixture = TestBed.createComponent(AtividadeludicaListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
