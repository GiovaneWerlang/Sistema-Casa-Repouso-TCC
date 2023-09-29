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

@Component({
  selector: 'app-profissional-cadastrar',
  templateUrl: './profissional-cadastrar.component.html',
  styleUrls: ['./profissional-cadastrar.component.css'],
  providers: [ProfissionalService]
})
export class ProfissionalCadastrarComponent implements OnInit {

  form: FormGroup;
  opcoesSituacao:LabelValue[] = Situacoes;
  opcoesFuncao:LabelValue[] = Funcoes;
  opcoesEspecialidade:LabelValue[] = [];
  opcoesEstados:string[] = Estados;
  opcoesPaises:string[] = Paises;

  constructor(
    private route: ActivatedRoute,
    private profissionalService: ProfissionalService,
    private especialidadeService: EspecialidadeService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {
    this.form = this.formBuilder.group({
      id: [null],
      nome: ['', Validators.required],
      idade: [0, Validators.required],
      cpf: ['', Validators.required],
      telefone: ['', Validators.required],
      email: ['', Validators.required],
      salario: [0, Validators.required],
      dataAdmissao: [new Date, Validators.required],
      funcao: ['CUIDADOR',Validators.required],
      situacao: ['ATIVO',Validators.required],
      especialidade: [null, Validators.required],
      endereco: this.formBuilder.group({
      logradouro: ['', Validators.required],
      numero: ['', Validators.required],
      bairro: ['', Validators.required],
      municipio: ['', Validators.required],
      cep: ['', Validators.required],
      estado: ['', Validators.required],
      pais: ['Brasil', Validators.required]
      })
    });
    this.carregarOpcoesEspecialidade();
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
    this.profissionalService.save(this.form.getRawValue()).subscribe((res) => {
      if(res){
        this.router.navigate(['/profissional/listar']);
      }
    })
    this.limpar();
  }

  limpar() {
    this.form.reset();
  }

  voltar(){
    this.router.navigate(['/profissional/listar']);
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

  private carregarOpcoesEspecialidade(){
    this.especialidadeService.list().subscribe((res) => 
      this.opcoesEspecialidade = res?.map((i:Especialidade) => ({label:i.nome, value:i.id}))
    );
  }

}
