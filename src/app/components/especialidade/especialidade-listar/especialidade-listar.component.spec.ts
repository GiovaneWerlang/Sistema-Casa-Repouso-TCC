import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { EspecialidadeListarComponent } from './especialidade-listar.component';
import { BlockUIModule } from 'primeng/blockui';
import { ButtonModule } from 'primeng/button';
import { PaginatorModule } from 'primeng/paginator';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { TableModule } from 'primeng/table';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { CrudTableComponent } from 'src/app/shared/crud-table/crud-table/crud-table.component';

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
        ToastModule,
      ],
      providers: [MessageService]
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
