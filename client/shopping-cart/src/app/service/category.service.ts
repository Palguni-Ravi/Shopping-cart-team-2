import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../models/category.model'; // Import your Category model

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  private baseUrl = 'http://localhost:8080'; // Replace with your actual backend API URL

  constructor(private http: HttpClient) {}

  addCategory(categoryData: Category): Observable<Category> {
    return this.http.post<Category>(`${this.baseUrl}/api/categories`, categoryData);
  }
}
