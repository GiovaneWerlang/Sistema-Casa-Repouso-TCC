import { HttpClient, HttpErrorResponse, HttpParams } from "@angular/common/http";
import { tap, Observable, catchError, throwError } from "rxjs";
import { environment } from "src/environments/environment.development";

export class CrudService<T> {

    private API_URL = environment.apiUrl;

    constructor(protected http: HttpClient, private T_URL: string) { }

    getUrl(){
        return this.T_URL;
    }

    list() {
        return this.http.get<T[]>(`${this.API_URL}${this.T_URL}`)
            .pipe(
                tap(console.log)
            );
    }

    page(page:number, size: number): Observable<any[]> {
        let params = new HttpParams()
        .set('page', page.toString())
        .set('size', size.toString());
        return this.http.get<T[]>(`${this.API_URL}${this.T_URL}?${params.toString()}`).pipe(
            catchError(this.handleError)
        );
    }

    findByID(id: number): Observable<any> {
        return this.http.get<T>(`${this.API_URL}${this.T_URL}/${id}`).pipe(
            catchError(this.handleError)
        );
    }

    create(record: T): Observable<any> {
        return this.http.post(`${this.API_URL}${this.T_URL}`, record).pipe(
            catchError(this.handleError)
        );
    }

    update(record: T): Observable<any> {
        return this.http.put(`${this.API_URL}${this.T_URL}/${record['id' as keyof T]}`, record).pipe(
            catchError(this.handleError)
        );
    }

    save(record: T) {
        if (record['id' as keyof T]) {
            return this.update(record);
        }        
        return this.create(record);
    }

    delete(id: number): Observable<any> {
        return this.http.delete(`${this.API_URL}${this.T_URL}/${id}`).pipe(
            catchError(this.handleError)
        );
    }

    private handleError(error: HttpErrorResponse) {
        return throwError(() => new Error(error.status.toString()));
    }
}