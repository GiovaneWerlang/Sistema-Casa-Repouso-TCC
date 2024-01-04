import { Component, OnInit } from '@angular/core';
import { MedicamentousoService } from '../service/medicamentouso.service';
import { ResidenteService } from '../../residente/service/residente.service';
import { MedicamentoestoqueService } from '../../medicamentoestoque/service/medicamentoestoque.service';
import { ActivatedRoute, Router } from '@angular/router';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MedicamentoEstoque } from '../../medicamentoestoque/modelo/medicamentoestoque';
import { Residente } from '../../residente/modelo/residente';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-medicamentouso-cadastrar',
  templateUrl: './medicamentouso-cadastrar.component.html',
  styleUrls: ['./medicamentouso-cadastrar.component.css'],
  providers: [MedicamentousoService, MessageService]
})
export class MedicamentousoCadastrarComponent implements OnInit {

  form: FormGroup;
  opcoesMedicamento: LabelValue[] = [];
  opcoesResidente: LabelValue[] = [];

  constructor(
    private route: ActivatedRoute,
    private medicamentoUsoService: MedicamentousoService,
    private medicamentoEstoqueService: MedicamentoestoqueService,
    private residenteService: ResidenteService,
    private formBuilder: FormBuilder,
    private router: Router,
    private messageService: MessageService
  ) {
    this.form = this.formBuilder.group({
      id: [null],
      intervalo: [null, [Validators.required, Validators.max(2147483647)]],
      qtdeVezesAoDia: [null, [Validators.required, Validators.min(1), Validators.max(24)]],
      dataHoraInicio: [null, [Validators.required]],
      qtdeDiasUso: [null, [Validators.required, Validators.min(1), Validators.max(2147483647)]],
      qtdeMedicamento: [null, [Validators.required, Validators.min(1), Validators.max(2147483647)]],
      residente: [null, Validators.required],
      medicamento: [null, Validators.required],
    });
    this.carregarOpcoesMedicamento();
    this.carregarOpcoesResidente();
    this.monitoraIntervalo();
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
      this.medicamentoUsoService.save(this.form.getRawValue()).subscribe((res) => {
        if (res) {
          this.router.navigate(['/medicamentouso/listar']);
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
    this.router.navigate(['/medicamentouso/listar']);
  }

  private carrega(id: number) {
    this.medicamentoUsoService.findByID(id).subscribe((medicamentoUso) => {
      this.form.patchValue(medicamentoUso);
      this.form.get('dataHoraInicio')?.patchValue(new Date(medicamentoUso.dataHoraInicio));

      this.form.get('medicamento')?.patchValue(medicamentoUso.medicamento.id);
      this.form.get('residente')?.patchValue(medicamentoUso.residente.id);

    });
  }

  private carregarOpcoesMedicamento() {
    this.medicamentoEstoqueService.list().subscribe((res) =>
      this.opcoesMedicamento = res?.map((i: MedicamentoEstoque) => ({ label: i.id + ' - ' + i.nome, value: i.id }))
    );
  }

  private carregarOpcoesResidente() {
    this.residenteService.list().subscribe((res) =>
      this.opcoesResidente = res?.map((i: Residente) => ({ label: i.id + ' - ' + i.nome, value: i.id }))
    );
  }

  monitoraIntervalo(){
    this.form.get('intervalo')?.valueChanges.subscribe((intervalo) => {
      if(intervalo && intervalo >= 24){
        this.form.get('qtdeVezesAoDia')?.clearValidators();
        this.form.get('qtdeVezesAoDia')?.addValidators([Validators.required, Validators.min(1), Validators.max(1)]);
        this.form.get('qtdeVezesAoDia')?.updateValueAndValidity();
      }else{
        this.form.get('qtdeVezesAoDia')?.clearValidators();
        this.form.get('qtdeVezesAoDia')?.addValidators([Validators.required, Validators.min(1), Validators.max(24)]);
        this.form.get('qtdeVezesAoDia')?.updateValueAndValidity();
      }
    })
  }

}
