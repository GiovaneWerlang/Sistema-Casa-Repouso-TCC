import { Component } from '@angular/core';
import { ConfiguracaoSistemaService } from '../service/configuracaosistema.service';
import { MessageService } from 'primeng/api';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-configuracaosistema-cadastrar',
  templateUrl: './configuracaosistema-cadastrar.component.html',
  styleUrls: ['./configuracaosistema-cadastrar.component.css'],
  providers: [ConfiguracaoSistemaService, MessageService]
})
export class ConfiguracaosistemaCadastrarComponent {
  form: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private configuracaoSistemaService: ConfiguracaoSistemaService,
    private formBuilder: FormBuilder,
    private messageService: MessageService
  ) {
    this.form = this.formBuilder.group({
      id: [null],
      habilitarEnvioEmail: [false, Validators.required],
      emailLogin: ['', Validators.maxLength(255)],
      emailSenha: ['', Validators.maxLength(255)],
      habilitarEnvioWhats: [false, Validators.required],
      whatsNumeroId: ['', Validators.maxLength(255)],
      whatsToken: ['', Validators.maxLength(500)]
    });
    this.monitoraCampoHabilitaEnvioEmail();
    this.monitoraCampoHabilitaEnvioWhats();
  }

  ngOnInit(): void {
    this.carrega();
  }

  monitoraCampoHabilitaEnvioEmail(){
    this.form.get('habilitarEnvioEmail')?.valueChanges.subscribe((habilitaEmail) => {
      console.log('email',habilitaEmail)
      if(habilitaEmail){
        this.form.get('emailLogin')?.addValidators(Validators.required);
        this.form.get('emailLogin')?.updateValueAndValidity();

        this.form.get('emailSenha')?.addValidators(Validators.required);
        this.form.get('emailSenha')?.updateValueAndValidity();
      }else{
        this.form.get('emailLogin')?.removeValidators(Validators.required);
        this.form.get('emailLogin')?.updateValueAndValidity();

        this.form.get('emailSenha')?.removeValidators(Validators.required);
        this.form.get('emailSenha')?.updateValueAndValidity();
      }
    })
  }

  monitoraCampoHabilitaEnvioWhats(){
    this.form.get('habilitarEnvioWhats')?.valueChanges.subscribe((habilitaWhats) => {
      console.log('whats',habilitaWhats)
      if(habilitaWhats){
        this.form.get('whatsNumeroId')?.addValidators(Validators.required);
        this.form.get('whatsNumeroId')?.updateValueAndValidity();

        this.form.get('whatsToken')?.addValidators(Validators.required);
        this.form.get('whatsToken')?.updateValueAndValidity();
      }else{
        this.form.get('whatsNumeroId')?.removeValidators(Validators.required);
        this.form.get('whatsNumeroId')?.updateValueAndValidity();

        this.form.get('whatsToken')?.removeValidators(Validators.required);
        this.form.get('whatsToken')?.updateValueAndValidity();
      }
    })
  }


  salvar() {
    if (this.form.valid) {
      this.configuracaoSistemaService.update(this.form.getRawValue()).subscribe((res) => {
        if (res) {
          this.messageService.add({ severity: 'success', summary: 'Sucesso!', detail: 'Configuração salva com sucesso.' });
          this.carrega();
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

  private carrega() {
    this.configuracaoSistemaService.find().subscribe((configuracaoSistema) => {
      this.form.patchValue(configuracaoSistema);
    });
  }
}
