import { HttpClientTestingModule } from "@angular/common/http/testing";
import { ComponentFixture, TestBed } from "@angular/core/testing";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { RouterTestingModule } from "@angular/router/testing";
import { ButtonModule } from "primeng/button";
import { ToastModule } from "primeng/toast";
import { TooltipModule } from "primeng/tooltip";
import { ConfiguracaosistemaCadastrarComponent } from "./configuracaosistema-cadastrar.component";

describe('ConfiguracaosistemaCadastrarComponent', () => {
    let component: ConfiguracaosistemaCadastrarComponent;
    let fixture: ComponentFixture<ConfiguracaosistemaCadastrarComponent>;
  
    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [ConfiguracaosistemaCadastrarComponent],
        imports: [
          RouterTestingModule, 
          HttpClientTestingModule,
          FormsModule,
          ReactiveFormsModule,
          ButtonModule,
          ToastModule,
          TooltipModule,
        ],
      });
      fixture = TestBed.createComponent(ConfiguracaosistemaCadastrarComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });
  
    it('should create', () => {
      expect(component).toBeTruthy();
    });
  });
  