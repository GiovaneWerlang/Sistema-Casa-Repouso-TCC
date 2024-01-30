import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Observable } from "rxjs";

export class DataHoraInterceptorService implements HttpInterceptor {
   
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
       if(req.method === 'POST' || req.method === 'PUT'){
        this.alterarDatas(req.body);
       }
       return next.handle(req);
    }

    alterarDatas(body:any){
        if(body === null || body === undefined){
            return body;
        }

        if(typeof body !== 'object'){
            return body;
        }

        for(const key of Object.keys(body)){
            const value = body[key];
            if(value instanceof Date){
                body[key] = new Date(Date.UTC(value.getFullYear(), value.getMonth(), value.getDate(), value.getHours(), value.getMinutes(), value.getSeconds()));
            }else if(typeof value === 'object'){
                this.alterarDatas(value);
            }
        }
    }

}