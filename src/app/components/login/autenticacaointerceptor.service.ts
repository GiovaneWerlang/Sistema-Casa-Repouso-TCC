import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Observable } from "rxjs";

export class AutenticacaoInterceptorService implements HttpInterceptor {

    headers:HttpHeaders | undefined;
    
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if(!req.url.includes('login')){
            let dados = localStorage.getItem('dadosUsuario');
            let token = '';
            if(dados){
                token = JSON.parse(dados)._token;
            }
            const modifiedReq = req.clone({
                headers: req.headers.append('Authorization','Bearer ' + token)
            })
            return next.handle(modifiedReq);
        }

        return next.handle(req);
    }

}