import { Component, OnInit } from '@angular/core';
import { MedicamentoestoqueService } from '../service/medicamentoestoque.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-medicamentoestoque-cadastrar',
  templateUrl: './medicamentoestoque-cadastrar.component.html',
  styleUrls: ['./medicamentoestoque-cadastrar.component.css'],
  providers: [MedicamentoestoqueService]
})
export class MedicamentoestoqueCadastrarComponent implements OnInit {

  form: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private medicamentoEstoqueService: MedicamentoestoqueService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {
    this.form = this.formBuilder.group({
      id: [null],
      nome: ['', Validators.required],
      principioAtivo: ['', Validators.required],
      qtde: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(c => {
      let id = c['id'];
      if (id != null) {
        this.carregaMedicamentoEstoque(Number(id));
      }
    })
  }

  salvar() {
    this.medicamentoEstoqueService.save(this.form.getRawValue()).subscribe((res) => {
      if (res) {
        this.router.navigate(['/medicamentoestoque/listar']);
      }
    })
    this.limpar();
  }

  limpar() {
    this.form.reset();
  }

  voltar() {
    this.router.navigate(['/medicamentoestoque/listar']);
  }

  private carregaMedicamentoEstoque(id: number) {
    this.medicamentoEstoqueService.findByID(id).subscribe((medicamentoEstoque) => this.form.setValue(medicamentoEstoque));
  }
}
