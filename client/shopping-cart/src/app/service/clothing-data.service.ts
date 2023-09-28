import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ClothingDataService {

  private baseUrl = "http://localhost:8080";
  private aggregationsSubject = new BehaviorSubject<any>(null);
  constructor(private http: HttpClient) { }

  getProducts(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}`+"/clothingUrl");
  }

  getIndexPageItems(): Observable<any> {
    
    return this.http.get<any>(`${this.baseUrl}`+"/indexPageItems");
  }

  getBrands(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}` + "/allBrands");
  }

  getCategory(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}` + "/allCategory");
  }

  getGender(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}` + "/allGender");
  }


  getSearchClothing(query: string): Observable<any> {
    const params = new HttpParams().set('q', query);
    return this.http.get(`${this.baseUrl}`+"/search", { params });
  }

  getSearchClothingUpdate(query: string, filter: string): Observable<any> {
    let params = new HttpParams()
      .set('q', query)
      .set('f', filter);

    return this.http.get(`${this.baseUrl}/updateSearch`, { params });
  }

  addProduct(productData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/addCloth`, productData);
  }

  aggregations$ = this.aggregationsSubject.asObservable();

  setAggregations(data: any) {
    this.aggregationsSubject.next(data);

  }
}
