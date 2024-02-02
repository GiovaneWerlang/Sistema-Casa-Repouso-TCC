import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanDeactivate, RouterStateSnapshot, UrlTree } from "@angular/router";
import { ConfirmationService } from "primeng/api";
import { Observable, Observer } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class CrudGuard implements CanDeactivate<any> {

    constructor(private confirmationService: ConfirmationService) {
    }

    canDeactivate(
        component: any,
        currentRoute: ActivatedRouteSnapshot,
        currentState: RouterStateSnapshot,
        nextState: RouterStateSnapshot
    ): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
        console.log(component?.form?.dirty)

        if (!component?.form?.dirty) {
            return true;
        }

        return new Observable((observer: Observer<boolean>) => {
            
            this.confirmationService.confirm({
                message: 'Tem certeza que descartar as alterações?',
                header: 'Confirmação',
                icon: 'pi pi-info-circle',
                acceptButtonStyleClass: "p-button-danger p-button-text",
                rejectButtonStyleClass: "p-button-text p-button-text",
                acceptIcon: "none",
                rejectIcon: "none",
                accept: () => {
                    observer.next(true);
                    observer.complete();
                },
                reject: () => {
                    observer.next(false);
                    observer.complete();
                }
            });
        });        
    }
}