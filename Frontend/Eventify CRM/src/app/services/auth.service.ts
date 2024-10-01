import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8082/api/auth';
  private roles: number[] = [];

  constructor(private http: HttpClient) {}

  login(loginData: { username: string; password: string }): Observable<any> {
    return this.http.post<any>(this.apiUrl + "/login", loginData).pipe(
      tap(response => {
        if (response && response.accessToken) {
          localStorage.setItem('accessToken', response.accessToken);
          localStorage.setItem('roles', JSON.stringify(response.roles)); // Store roles
          localStorage.setItem('userId', response.user.userId);
          this.roles = response.roles; // Set roles locally as well
        }
      })
    );
  }

  register(formValues: any): Observable<any> {
    console.log('Form values:', formValues);
    return this.http.post<any>(`${this.apiUrl}/register`, formValues);
  }

  logout(): void {
    localStorage.removeItem('accessToken');
    localStorage.removeItem('roles');
    this.roles = [];
  }

  isAuthenticated(): boolean {
    return !!localStorage.getItem('accessToken');
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

  // New method to get user profile using the access token
  getUserDetails(): Observable<any> {
    const token = localStorage.getItem('accessToken');
    const userId = localStorage.getItem('userId');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get(`http://localhost:8082/api/users/${userId}`, { headers });
  }

  getUserId(): number | null {
    return localStorage.getItem('userId') ? +localStorage.getItem('userId') : null; // Convert to number or return null
  }
  
}
