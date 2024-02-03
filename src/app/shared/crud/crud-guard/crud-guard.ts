import { inject } from "@angular/core";
import { ActivatedRouteSnapshot, CanDeactivateFn, RouterStateSnapshot } from "@angular/router";
import { ConfirmationService } from "primeng/api";
import { Observable, Observer } from "rxjs";

export const CrudGuard: CanDeactivateFn<any> = (
    component: any,
    currentRoute: ActivatedRouteSnapshot,
    currentState: RouterStateSnapshot,
    nextState: RouterStateSnapshot
) => {

    const confirmationService = inject(ConfirmationService);

    if (!component?.form?.dirty) {
        return true;
    }

    return new Observable((observer: Observer<boolean>) => {

        confirmationService.confirm({
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