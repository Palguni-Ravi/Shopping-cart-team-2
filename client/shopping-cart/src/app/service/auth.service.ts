import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable} from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}
  private baseUrl = 'http://localhost:8080';
  getUserdetails(email: string): Observable<boolean> {
    return this.http.get<boolean>(`${this.baseUrl}` + `/auth/${email}`);
  }

  createUserdetails(user: User): Observable<User> {
    return this.http.post<User>(
      `${this.baseUrl}` + '/auth/createUser',
      user
    );
  }

  validatePassword(email: string, password: string): Observable<string> {
    return this.http.get<string>(
      `${this.baseUrl}` + `/auth/${email}/${password}`
    );
  }
  getUserRole(email: string): Observable<string> {
    return this.http.get(
      `${this.baseUrl}` + `/auth/getUserRole/${email}`,
      { responseType: 'text' }
    );
  }

}
