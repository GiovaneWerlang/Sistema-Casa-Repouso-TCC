import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../service/usuario.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProfissionalService } from '../../profissional/service/profissional.service';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { Profissional } from '../../profissional/modelo/profissional';
import { ToastService } from 'src/app/shared/toast-service/toast.service';

@Component({
  selector: 'app-usuario-cadastrar',
  templateUrl: './usuario-cadastrar.component.html',
  styleUrls: ['./usuario-cadastrar.component.css'],
  providers: [UsuarioService]
})
export class UsuarioCadastrarComponent implements OnInit {

  form: FormGroup;
  novo:boolean = false;
  opcoesProfissional: LabelValue[] = [];

  constructor(
    private route: ActivatedRoute,
    private usuarioService: UsuarioService,
    private profissionalService: ProfissionalService,
    private formBuilder: FormBuilder,
    private router: Router,
    private toastService: ToastService
  ) {
    this.form = this.formBuilder.group({
      id: [null],
      login: [null, Validators.compose([Validators.required, Validators.maxLength(50)])],
      senha: [null, Validators.compose([Validators.required, Validators.minLength(6)])],
      profissional: [null, Validators.required]
    });
    this.carregarOpcoesProfissional();
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
      this.usuarioService.save(this.form.getRawValue()).subscribe((res) => {
        if (res) {
          this.toastService.toastSuccess(`Usuário  ${this.novo ? 'salvo' : 'atualizado'}  com sucesso.`);
          this.router.navigate(['/usuario/listar']);
        }
      })
      this.limpar();
    } else {
      this.form.markAllAsTouched();
      this.form.get('logradouro')?.markAsTouched();
      this.toastService.toastWarning('Não foi possível salvar!', 'Verifique os campos e tente novamente.');
    }
  }

  limpar() {
    this.form.reset();
  }

  voltar() {
    this.router.navigate(['/usuario/listar']);
  }

  private carrega(id: number) {
    this.usuarioService.findByID(id).subscribe((usuario) => {
      this.form.get('id')?.patchValue(usuario.id);
      this.form.get('login')?.patchValue(usuario.login);
      this.form.get('profissional')?.patchValue(usuario.profissional.id);
    });
  }

  private carregarOpcoesProfissional() {
    this.profissionalService.list().subscribe((res) =>
      this.opcoesProfissional = res?.map((i: Profissional) => ({ label: i.id + ' - ' + i.nome, value: i.id }))
    );
  }

}
