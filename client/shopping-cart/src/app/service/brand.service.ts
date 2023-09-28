import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class BrandService {
  private baseUrl = 'http://localhost:8080'; // Update with your API base URL

  constructor(private http: HttpClient) {}

  addBrand(brandData: any): Observable<any> {
    const url = `${this.baseUrl}/brands`; // Update with your brand endpoint URL

    // Send a POST request to create a new brand
    return this.http.post(url, brandData);
  }
}

// import { HttpClient } from '@angular/common/http';
// import { Injectable } from '@angular/core';
// import { Observable } from 'rxjs';
// import { Brand } from '../models/brand.model'; // Import your Category model

// @Injectable({
//   providedIn: 'root',
// })
// export class CategoryService {
//   private baseUrl = 'http://localhost:8080'; // Replace with your actual backend API URL

//   constructor(private http: HttpClient) {}

//   addBrand(brandData: Brand): Observable<Brand> {
//     return this.http.post<Brand>(`${this.baseUrl}/api/brand`, brandData);
//   }
// }
