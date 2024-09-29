import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8082/api/auth';
  private roles: number[] = [];

  constructor(private http: HttpClient) {}

  login(loginData: { username: string; password: string }): Observable<any> {

    return this.http.post<any>(this.apiUrl + "/login", loginData);
  }

  register(formValues: any): Observable<any> {
    console.log('Form values:', formValues);
    return this.http.post<any>(`${this.apiUrl}/register`, formValues);
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('roles');
    this.roles = [];
  }

  isAuthenticated(): boolean {
    return !!localStorage.getItem('token');
  }

  private getRoles(): number[] {
    const roles = localStorage.getItem('roles');
    return roles ? JSON.parse(roles) : [];
  }

  hasRole(roleId: number): boolean {
    return this.getRoles().includes(roleId);
  }

  hasAnyRole(roleIds: number[]): boolean {
    return roleIds.some(roleId => this.hasRole(roleId));
  }

}
