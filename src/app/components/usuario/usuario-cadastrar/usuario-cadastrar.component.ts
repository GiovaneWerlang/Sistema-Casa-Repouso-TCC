import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../service/usuario.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProfissionalService } from '../../profissional/service/profissional.service';
import { LabelValue } from 'src/app/shared/labelvalue/labelvalue';
import { Profissional } from '../../profissional/modelo/profissional';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-usuario-cadastrar',
  templateUrl: './usuario-cadastrar.component.html',
  styleUrls: ['./usuario-cadastrar.component.css'],
  providers: [UsuarioService, MessageService]
})
export class UsuarioCadastrarComponent implements OnInit {

  form: FormGroup;
  opcoesProfissional: LabelValue[] = [];

  constructor(
    private route: ActivatedRoute,
    private usuarioService: UsuarioService,
    private profissionalService: ProfissionalService,
    private formBuilder: FormBuilder,
    private router: Router,
    private messageService: MessageService
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
      if (id != null) {
        this.carrega(Number(id));
      }
    })
  }

  salvar() {
    if (this.form.valid) {
      this.usuarioService.save(this.form.getRawValue()).subscribe((res) => {
        if (res) {
          this.router.navigate(['/usuario/listar']);
        }
      })
      this.limpar();
    } else {
      this.form.markAllAsTouched();
      this.form.get('logradouro')?.markAsTouched();
      this.messageService.add({ severity: 'warn', summary: 'Não foi possível salvar!', detail: 'Verifique os campos e tente novamente.' });
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
