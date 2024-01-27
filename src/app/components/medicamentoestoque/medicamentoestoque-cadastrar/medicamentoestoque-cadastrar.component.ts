import { Component, OnInit } from '@angular/core';
import { MedicamentoestoqueService } from '../service/medicamentoestoque.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastService } from 'src/app/shared/toast-service/toast.service';

@Component({
  selector: 'app-medicamentoestoque-cadastrar',
  templateUrl: './medicamentoestoque-cadastrar.component.html',
  styleUrls: ['./medicamentoestoque-cadastrar.component.css'],
  providers: [MedicamentoestoqueService]
})
export class MedicamentoestoqueCadastrarComponent implements OnInit {

  form: FormGroup;
  novo:boolean = false;
  constructor(
    private route: ActivatedRoute,
    private medicamentoEstoqueService: MedicamentoestoqueService,
    private formBuilder: FormBuilder,
    private router: Router,
    private toastService: ToastService
  ) {
    this.form = this.formBuilder.group({
      id: [null],
      nome: ['', [Validators.required, Validators.maxLength(100)]],
      principioAtivo: ['', [Validators.required, Validators.maxLength(255)]],
      qtde: [null, [Validators.required, Validators.max(2147483647)]]
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(c => {
      let id = c['id'];
      if (id) {
        this.carrega(Number(id));
      }
      this.novo = id ? false : true;
    })
  }

  salvar() {
    if (this.form.valid) {
      this.medicamentoEstoqueService.save(this.form.getRawValue()).subscribe((res) => {
        if (res) {
          this.toastService.toastSuccess(`Medicamento  ${this.novo ? 'salvo' : 'atualizado'}  com sucesso.`);
          this.router.navigate(['/medicamentoestoque/listar']);
        }
      })
      this.limpar();
    } else {
      this.form.markAllAsTouched();
      this.toastService.toastWarning('Não foi possível salvar!', 'Verifique os campos e tente novamente.');
    }
  }

  limpar() {
    this.form.reset();
  }

  voltar() {
    this.router.navigate(['/medicamentoestoque/listar']);
  }

  private carrega(id: number) {
    this.medicamentoEstoqueService.findByID(id).subscribe((medicamentoEstoque) => this.form.setValue(medicamentoEstoque));
  }
}
