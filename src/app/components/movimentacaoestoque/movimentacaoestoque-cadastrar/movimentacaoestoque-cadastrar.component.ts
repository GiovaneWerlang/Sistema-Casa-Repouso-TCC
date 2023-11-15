import { Component, OnInit } from '@angular/core';
import { MovimentacaoestoqueService } from '../service/movimentacaoestoque.service';
import { TipoMovimentacao } from 'src/app/shared/tipomovimentacao/tipomovimentacao';
import { ActivatedRoute, Router } from '@angular/router';
import { MedicamentoestoqueService } from '../../medicamentoestoque/service/medicamentoestoque.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { MedicamentoEstoque } from '../../medicamentoestoque/modelo/medicamentoestoque';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-movimentacaoestoque-cadastrar',
  templateUrl: './movimentacaoestoque-cadastrar.component.html',
  styleUrls: ['./movimentacaoestoque-cadastrar.component.css'],
  providers: [MovimentacaoestoqueService, MessageService]
})
export class MovimentacaoestoqueCadastrarComponent implements OnInit {

  form: FormGroup;
  opcoesTipo: LabelValue[] = TipoMovimentacao;
  opcoesMedicamento: LabelValue[] = [];

  constructor(
    private route: ActivatedRoute,
    private movimentacaoEstoqueService: MovimentacaoestoqueService,
    private medicamentoEstoqueService: MedicamentoestoqueService,
    private formBuilder: FormBuilder,
    private router: Router,
    private messageService: MessageService
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
      if (id != null) {
        this.carrega(Number(id));
      }
    })
  }

  salvar() {
    if (this.form.valid) {
      this.movimentacaoEstoqueService.save(this.form.getRawValue()).subscribe((res) => {
        if (res) {
          this.router.navigate(['/movimentacaoestoque/listar']);
        }
      })
      this.limpar();
    } else {
      this.form.markAllAsTouched();
      this.messageService.add({ severity: 'warn', summary: 'Não foi possível salvar!', detail: 'Verifique os campos e tente novamente.' });
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
