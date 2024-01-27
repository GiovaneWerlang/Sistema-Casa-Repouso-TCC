import { Component, OnInit } from '@angular/core';
import { MovimentacaoestoqueService } from '../service/movimentacaoestoque.service';
import { TipoMovimentacao } from 'src/app/shared/tipomovimentacao/tipomovimentacao';
import { ActivatedRoute, Router } from '@angular/router';
import { MedicamentoestoqueService } from '../../medicamentoestoque/service/medicamentoestoque.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { MedicamentoEstoque } from '../../medicamentoestoque/modelo/medicamentoestoque';
import { ToastService } from 'src/app/shared/toast-service/toast.service';

@Component({
  selector: 'app-movimentacaoestoque-cadastrar',
  templateUrl: './movimentacaoestoque-cadastrar.component.html',
  styleUrls: ['./movimentacaoestoque-cadastrar.component.css'],
  providers: [MovimentacaoestoqueService]
})
export class MovimentacaoestoqueCadastrarComponent implements OnInit {

  form: FormGroup;
  novo:boolean = false;
  opcoesTipo: LabelValue[] = TipoMovimentacao;
  opcoesMedicamento: LabelValue[] = [];

  constructor(
    private route: ActivatedRoute,
    private movimentacaoEstoqueService: MovimentacaoestoqueService,
    private medicamentoEstoqueService: MedicamentoestoqueService,
    private formBuilder: FormBuilder,
    private router: Router,
    private toastService: ToastService
  ) {
    this.form = this.formBuilder.group({
      id: [null],
      tipo: ['ENTRADA', Validators.required],
      qtde: [0, [Validators.required, Validators.min(1), Validators.max(2147483647)]],
      medicamento: [null, Validators.required],
    });
    this.carregarOpcoesMedicamento();
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
      this.movimentacaoEstoqueService.save(this.form.getRawValue()).subscribe((res) => {
        if (res) {
          this.toastService.toastSuccess(`Movimentação de medicamento ${this.novo ? 'salva' : 'atualizada'} com sucesso.`);
          this.router.navigate(['/movimentacaoestoque/listar']);
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
    this.router.navigate(['/movimentacaoestoque/listar']);
  }

  private carrega(id: number) {
    this.movimentacaoEstoqueService.findByID(id).subscribe((movimentacaoEstoque) => {
      this.form.patchValue(movimentacaoEstoque);

      this.form.get('medicamento')?.patchValue(movimentacaoEstoque.medicamento.id);
    });
  }

  private carregarOpcoesMedicamento() {
    this.medicamentoEstoqueService.list().subscribe((res) =>
      this.opcoesMedicamento = res?.map((i: MedicamentoEstoque) => ({ label: i.id + ' - ' + i.nome, value: i.id }))
    );
  }

}
