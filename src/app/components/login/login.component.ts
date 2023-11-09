import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { Router } from '@angular/router';
import { AutenticacaoService } from './service/autenticacao.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [MessageService]
})
export class LoginComponent {
  form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private autenticacaoService: AutenticacaoService,
    private router: Router,
  ) {
    this.form = this.formBuilder.group({
      login: ['', [Validators.required, Validators.maxLength(50)]],
      senha: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  login() {
    if (this.form.valid) {
      this.autenticacaoService.login({ login: this.form.get('login')?.value, senha: this.form.get('senha')?.value }).subscribe(
        (next) => {
          this.router.navigate(['/home']);
        },
        (error) => {
          this.messageService.add({ severity: 'error', summary: 'Erro!', detail: error });
        }
      );
    } else {
      this.form.markAllAsTouched();
      this.messageService.add({ severity: 'warn', summary: 'Não foi possível logar!', detail: 'Verifique os campos e tente novamente.' });
    }
  }

  logout() {
    this.autenticacaoService.logout();
  }

}
