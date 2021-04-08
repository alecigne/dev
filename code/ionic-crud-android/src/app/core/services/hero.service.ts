import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { Hero } from '../models';
import { environment } from '../../../environments/environment';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class HeroService {

  constructor(
    private http: HttpClient
  ) { }

  /** CREATE a new hero on the server */
  add(hero: Hero): Observable<Hero> {
    return this.http.post<Hero>(environment.api, hero, httpOptions).pipe(
      tap((h: Hero) => console.log(`added hero w/ id=${h.id}`))
    );
  }

  /** READ hero by id */
  getOne(id: number): Observable<Hero> {
    const url = `${environment.api}/${id}`;
    return this.http.get<Hero>(url).pipe(
      tap(_ => console.log(`fetched hero id=${id}`))
    );
  }

  /** READ all heroes from the server */
  getAll(): Observable<Hero[]> {
    return this.http.get<Hero[]>(environment.api)
      .pipe(
        tap(heroes => console.log('fetched heroes'))
      );
  }

  /** PUT: update the hero on the server */
  update(hero: Hero): Observable<any> {
    const url = `${environment.api}/${hero.id}`;
    return this.http.put(url, hero, httpOptions).pipe(
      tap(_ => console.log(`updated hero id=${hero.id}`))
    );
  }

  /** DELETE the hero from the server */
  delete(hero: Hero | number): Observable<Hero> {
    const id = typeof hero === 'number' ? hero : hero.id;
    const url = `${environment.api}/${id}`;

    return this.http.delete<Hero>(url, httpOptions).pipe(
      tap(_ => console.log(`deleted hero id=${id}`))
    );
  }

}
