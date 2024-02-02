import { ToastService } from './../../shared/toast-service/toast.service';
import { Injectable } from "@angular/core";
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable, map } from "rxjs";
import { AutenticacaoService } from "./service/autenticacao.service";

@Injectable({providedIn: 'root'})
export class RolesGuard implements CanActivate {

    constructor(private autenticacaoService:AutenticacaoService, private router:Router, private toastService: ToastService){
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {        
        return this.autenticacaoService.dadoUsuario
        .pipe(
            map((usuario) => {
                const funcao = usuario?.funcao;
                const rolesPermitidas = route.data?.['roles'];
                if(funcao && rolesPermitidas?.includes(funcao)){
                    return true;  
                }else{
                    this.toastService.toastBase('warn', 'Sem permissão de acesso!', 'Contate seu administrador.');
                    return this.router.createUrlTree(['/home']);
                }
            })
        );
    }

}