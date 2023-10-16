import { HttpClient, HttpErrorResponse, HttpHeaders } from "@angular/common/http";
import { tap, Observable, catchError, throwError } from "rxjs";
import { environment } from "src/environments/environment.development";

export class CrudService<T> {

    private API_URL = environment.apiUrl;
    headers:HttpHeaders;
    constructor(protected http: HttpClient, private T_URL: string) {
        let dados = localStorage.getItem('dadosUsuario');
        let token = '';
        if(dados){
            token = JSON.parse(dados).token;
        }
        this.headers = new HttpHeaders().set('Authorization','Bearer ' + token);
    }

    getUrl(){
        return this.T_URL;
    }

    list() {
        return this.http.get<T[]>(`${this.API_URL}${this.T_URL}`, { 'headers': this.headers })
            .pipe(
                tap(console.log)
            );
    }

    page(page:number, size: number): Observable<any[]> {
        
        return this.http.get<T[]>(`${this.API_URL}${this.T_URL}/page/${page}/${size}`, { 'headers': this.headers }).pipe(
            catchError(this.handleError)
        );
    }

    pagesort(page:number, size: number, sort:string, asc:boolean): Observable<any[]> {
        
        return this.http.get<T[]>(`${this.API_URL}${this.T_URL}/pagesort/${page}/${size}/${sort}/${asc}`, { 'headers': this.headers }).pipe(
            catchError(this.handleError)
        );
    }

    findByID(id: number): Observable<any> {
        return this.http.get<T>(`${this.API_URL}${this.T_URL}/${id}`, { 'headers': this.headers }).pipe(
            catchError(this.handleError)
        );
    }

    create(record: T): Observable<any> {
        return this.http.post(`${this.API_URL}${this.T_URL}`, record, { 'headers': this.headers }).pipe(
            catchError(this.handleError)
        );
    }

    update(record: T): Observable<any> {
        return this.http.put(`${this.API_URL}${this.T_URL}/${record['id' as keyof T]}`, record, { 'headers': this.headers }).pipe(
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
        return this.http.delete(`${this.API_URL}${this.T_URL}/${id}`, { 'headers': this.headers }).pipe(
            catchError(this.handleError)
        );
    }

    private handleError(error: HttpErrorResponse) {
        return throwError(() => new Error(error.status.toString()));
    }
}