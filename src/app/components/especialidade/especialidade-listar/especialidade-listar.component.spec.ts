import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { EspecialidadeListarComponent } from './especialidade-listar.component';
import { BlockUIModule } from 'primeng/blockui';
import { ButtonModule } from 'primeng/button';
import { PaginatorModule } from 'primeng/paginator';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { TableModule } from 'primeng/table';
import { ConfirmationService, MessageService } from 'primeng/api';
import { CrudTableComponent } from 'src/app/shared/crud-table/crud-table/crud-table.component';
import { ConfirmDialogModule } from 'primeng/confirmdialog';

describe('EspecialidadeListarComponent', () => {
  let component: EspecialidadeListarComponent;
  let fixture: ComponentFixture<EspecialidadeListarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EspecialidadeListarComponent, CrudTableComponent ],
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
      providers: [MessageService, ConfirmationService]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EspecialidadeListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
