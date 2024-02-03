import { AutenticacaoService } from './service/autenticacao.service';
import { inject } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from "@angular/router";
import { map } from "rxjs";

export const AutenticacaoGuard: CanActivateFn = (
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
) => {
    const autenticacaoService = inject(AutenticacaoService);
    const router = inject(Router);

    return autenticacaoService.dadoUsuario
        .pipe(
            map((usuario) => {
                const autenticado = !!usuario;
                if (autenticado) {
                    if (state.url === '/login') {
                        return router.createUrlTree(['/home']);
                    } else {
                        return true;
                    }
                }
                return router.createUrlTree(['/login']);
            })
        );
}