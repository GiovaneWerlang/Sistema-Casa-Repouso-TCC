import { AutenticacaoService } from './service/autenticacao.service';
import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable, map, take } from "rxjs";

@Injectable({providedIn: 'root'})
export class AutenticacaoGuard implements CanActivate {

    constructor(private autenticacaoService:AutenticacaoService, private router:Router){
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
        return this.autenticacaoService.dadoUsuario
        .pipe(
            take(1),
            map((usuario) => {               
                const autenticado = !!usuario;
                if(autenticado){
                    return true;
                }
                return this.router.createUrlTree(['/login']);
            })
        );
    }

}