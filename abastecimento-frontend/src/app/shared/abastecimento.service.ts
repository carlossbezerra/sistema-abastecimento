import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Abastecimento } from './confirmation-dialog/abastecimento.model';
import { Page } from './confirmation-dialog/page.model';

@Injectable({
  providedIn: 'root'
})
export class AbastecimentoService {

  private baseUrl = 'http://localhost:8080/abastecimentos';

  constructor(private http: HttpClient) { }

  getAll(page: number, size: number, placa?: string): Observable<Page<Abastecimento>> {
    let params = new HttpParams()
      .set('page', page)
      .set('size', size)

    if (placa) {
      params = params.set('placa', placa)
    }
    return this.http.get<Page<Abastecimento>>(this.baseUrl, { params });
  }

  add(abastecimento: Abastecimento): Observable<Abastecimento> {
    return this.http.post<Abastecimento>(this.baseUrl, abastecimento);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}