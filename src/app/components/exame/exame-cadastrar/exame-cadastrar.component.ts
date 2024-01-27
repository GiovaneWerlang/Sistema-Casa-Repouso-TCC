import { ResidenteService } from './../../residente/service/residente.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { ExameService } from '../service/exame.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ProfissionalService } from '../../profissional/service/profissional.service';
import { EspecialidadeService } from '../../especialidade/service/especialidade.service';
import { Especialidade } from '../../especialidade/modelo/especialidade';
import { Profissional } from '../../profissional/modelo/profissional';
import { Residente } from '../../residente/modelo/residente';
import { ToastService } from 'src/app/shared/toast-service/toast.service';

@Component({
  selector: 'app-exame-cadastrar',
  templateUrl: './exame-cadastrar.component.html',
  styleUrls: ['./exame-cadastrar.component.css'],
  providers: [ExameService]
})
export class ExameCadastrarComponent implements OnInit {

  form: FormGroup;
  novo:boolean = false;
  opcoesEspecialidade: LabelValue[] = [];
  opcoesProfissional: LabelValue[] = [];
  opcoesResidente: LabelValue[] = [];

  constructor(
    private route: ActivatedRoute,
    private exameService: ExameService,
    private profissionalService: ProfissionalService,
    private especialidadeService: EspecialidadeService,
    private residenteService: ResidenteService,
    private formBuilder: FormBuilder,
    private router: Router,
    private toastService: ToastService
  ) {
    this.form = this.formBuilder.group({
      id: [null],
      descricao: ['', [Validators.required, Validators.maxLength(255)]],
      dataHora: [null, Validators.required],
      local: ['', [Validators.required, Validators.maxLength(100)]],
      laudo: ['', Validators.maxLength(500)],
      especialidade: [null, Validators.required],
      profissional: [null, Validators.required],
      residente: [null, Validators.required]
    });
    this.carregarOpcoesEspecialidade();
    this.carregarOpcoesProfissional();
    this.carregarOpcoesResidente();
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
      this.exameService.save(this.form.getRawValue()).subscribe((res) => {
        if (res) {
          this.toastService.toastSuccess(`Exame  ${this.novo ? 'salvo' : 'atualizado'}  com sucesso.`);
          this.router.navigate(['/exame/listar']);
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
    this.router.navigate(['/exame/listar']);
  }

  private carrega(id: number) {
    this.exameService.findByID(id).subscribe((exame) => {
      this.form.patchValue(exame);
      this.form.get('dataHora')?.patchValue(new Date(exame.dataHora));

      this.form.get('especialidade')?.patchValue(exame.especialidade.id);
      this.form.get('profissional')?.patchValue(exame.profissional.id);
      this.form.get('residente')?.patchValue(exame.residente.id);
    });
  }

  private carregarOpcoesEspecialidade() {
    this.especialidadeService.list().subscribe((res) =>
      this.opcoesEspecialidade = res?.map((i: Especialidade) => ({ label: i.id + ' - ' + i.nome, value: i.id }))
    );
  }

  private carregarOpcoesProfissional() {
    this.profissionalService.list().subscribe((res) =>
      this.opcoesProfissional = res?.map((i: Profissional) => ({ label: i.id + ' - ' + i.nome, value: i.id }))
    );
  }

  private carregarOpcoesResidente() {
    this.residenteService.list().subscribe((res) =>
      this.opcoesResidente = res?.map((i: Residente) => ({ label: i.id + ' - ' + i.nome, value: i.id }))
    );
  }

}
