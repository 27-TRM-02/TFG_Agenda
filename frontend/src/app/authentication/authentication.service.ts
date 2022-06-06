import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { SignUp } from './dto/sign-up';
import { User } from './dto/user';
import { environment } from 'src/environments/environment';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  constructor(
    private httpClient: HttpClient,
    private router: Router,
    private jwtService: JwtHelperService
  ) {}

  // Método login: si username y password son válidos, se redirige a home
  // Si los credenciales no son válidos, se le vuelve a redirigir al login
  public login(username: String, password: String): void {
    this.httpClient
      .post(`${environment.apiUrl}/auth/login`, {
        username,
        password,
      })
      .subscribe({
        next: (userOrError: any) => {
          // No ha habido errores
          localStorage.setItem('token', userOrError.jwt);
          localStorage.setItem('username', userOrError.username);
          this.router.navigate(['/']);
        },
        error: (error) => {
          // Ha habido algún error
          this.router.navigate(['/login']);
        },
      });
  }

  // Registra un nuevo usuario
  public signUp(newUser: SignUp): Observable<User> {
    return this.httpClient.put<User>(
      `${environment.apiUrl}/auth/signup`,
      newUser
    );
  }

  public logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('username');
    this.router.navigate(['/login']);
  }

  // Comprueba si el token del usuario está validado
  public userIsAuthenticated(): boolean {
    const token: string | null = this.getToken();
    return token !== null && !this.jwtService.isTokenExpired(token);
  }

  // Retorna el token del usuario activo
  public getToken(): string | null {
    return localStorage.getItem('token');
  }

  public getUsername(): string {
    return localStorage.getItem('username') || '';
  }
}
