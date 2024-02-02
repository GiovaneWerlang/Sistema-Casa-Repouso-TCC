import { Component, OnInit, Pipe } from '@angular/core';
import { ProfissionalService } from '../service/profissional.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { Situacoes } from 'src/app/shared/situacoes/situacoes';
import { Funcoes } from 'src/app/shared/funcoes/funcoes';
import { ActivatedRoute, Router } from '@angular/router';
import { EspecialidadeService } from '../../especialidade/service/especialidade.service';
import { Especialidade } from '../../especialidade/modelo/especialidade';
import { Estados } from 'src/app/shared/estados/estados';
import { Paises } from 'src/app/shared/paises/paises'
import { ToastService } from 'src/app/shared/toast-service/toast.service';

@Component({
  selector: 'app-profissional-cadastrar',
  templateUrl: './profissional-cadastrar.component.html',
  styleUrls: ['./profissional-cadastrar.component.css'],
  providers: [ProfissionalService]
})
export class ProfissionalCadastrarComponent implements OnInit {

  form: FormGroup;
  novo:boolean = false;

  formEndereco: FormGroup;
  opcoesSituacao: LabelValue[] = Situacoes;
  opcoesFuncao: LabelValue[] = Funcoes;
  opcoesEspecialidade: LabelValue[] = [];
  opcoesEstados: string[] = Estados;
  opcoesPaises: string[] = Paises;

  constructor(
    private route: ActivatedRoute,
    private profissionalService: ProfissionalService,
    private especialidadeService: EspecialidadeService,
    private formBuilder: FormBuilder,
    private router: Router,
    private toastService: ToastService
  ) {
    this.formEndereco = this.formBuilder.group({
      logradouro: ['', [Validators.required, Validators.maxLength(150)]],
      numero: ['', [Validators.required, Validators.maxLength(50)]],
      bairro: ['', [Validators.required, Validators.maxLength(100)]],
      municipio: ['', [Validators.required, Validators.maxLength(150)]],
      cep: ['', [Validators.required, Validators.maxLength(8), Validators.minLength(8)]],
      estado: ['Acre', Validators.required],
      pais: ['Brasil', Validators.required]
    });
    this.form = this.formBuilder.group({
      id: [null],
      nome: ['', [Validators.required, Validators.maxLength(150)]],
      idade: [0, [Validators.required, Validators.min(1), Validators.max(140)]],
      cpf: ['', Validators.required],
      telefone: ['', [Validators.required, Validators.maxLength(11), Validators.minLength(10)]],
      email: ['', [Validators.required, Validators.maxLength(100)]],
      salario: [0, [Validators.required, Validators.min(0)]],
      dataAdmissao: [new Date, Validators.required],
      funcao: ['CUIDADOR', Validators.required],
      situacao: ['ATIVO', Validators.required],
      especialidade: [null],
      endereco: this.formEndereco
    });
    this.carregarOpcoesEspecialidade();
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
      this.profissionalService.save(this.form.getRawValue()).subscribe((res) => {
        if (res) {
          this.toastService.toastSuccess(`Profissional ${this.novo ? 'salvo(a)' : 'atualizado(a)'} com sucesso.`);
          this.voltar();
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
    this.router.navigate([`/${this.profissionalService.getUrl()}/listar`]);
  }

  private carrega(id: number) {
    this.profissionalService.findByID(id).subscribe((profissional) => {
      this.form.patchValue(profissional);
      this.form.get('dataAdmissao')?.patchValue(new Date(profissional.dataAdmissao));

      this.form.get('especialidade')?.patchValue(profissional.especialidade.id);

      this.form.get('logradouro')?.patchValue(profissional.endereco.logradouro);
      this.form.get('bairro')?.patchValue(profissional.endereco.bairro);
      this.form.get('municipio')?.patchValue(profissional.endereco.municipio);
      this.form.get('numero')?.patchValue(profissional.endereco.numero);
      this.form.get('cep')?.patchValue(profissional.endereco.cep);
      this.form.get('estado')?.patchValue(profissional.endereco.estado);
      this.form.get('pais')?.patchValue(profissional.endereco.pais);
    });
  }

  private carregarOpcoesEspecialidade() {
    this.especialidadeService.list().subscribe((res:any) =>
      this.opcoesEspecialidade = res?.map((i: Especialidade) => ({ label: i.nome, value: i.id }))
    );
  }

}
