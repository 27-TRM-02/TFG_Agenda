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
          this.router.navigate(['/']);
        },
        error: (error) => {
          // Ha habido algún error
          this.router.navigate(['/login']);
        },
      });
  }

  public signUp(newUser: SignUp): Observable<User> {
    return this.httpClient.put<User>(
      `${environment.apiUrl}/auth/signup`,
      newUser
    );
  }

  public userIsAuthenticated(): boolean {
    // Comprobamos si el token está validado
    const token: string | null = this.getToken();
    return token !== null && !this.jwtService.isTokenExpired(token);
  }

  public getToken(): string | null {
    return localStorage.getItem('token');
  }
}
