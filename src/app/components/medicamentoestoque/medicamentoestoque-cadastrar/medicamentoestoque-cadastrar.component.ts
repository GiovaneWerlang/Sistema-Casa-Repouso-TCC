import { Component, OnInit } from '@angular/core';
import { MedicamentoestoqueService } from '../service/medicamentoestoque.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-medicamentoestoque-cadastrar',
  templateUrl: './medicamentoestoque-cadastrar.component.html',
  styleUrls: ['./medicamentoestoque-cadastrar.component.css'],
  providers: [MedicamentoestoqueService, MessageService]
})
export class MedicamentoestoqueCadastrarComponent implements OnInit {

  form: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private medicamentoEstoqueService: MedicamentoestoqueService,
    private formBuilder: FormBuilder,
    private router: Router,
    private messageService: MessageService
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
      if (id != null) {
        this.carregaMedicamentoEstoque(Number(id));
      }
    })
  }

  salvar() {
    if(this.form.valid){
      this.medicamentoEstoqueService.save(this.form.getRawValue()).subscribe((res) => {
        if(res){
          this.router.navigate(['/medicamentoestoque/listar']);
        }
      })
      this.limpar();
    }else{
      this.form.markAllAsTouched();
      this.messageService.add({ severity: 'warn', summary: 'Não foi possível salvar!', detail: 'Verifique os campos e tente novamente.' });
    }
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
