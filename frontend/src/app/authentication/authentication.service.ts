import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { SignUp } from './dto/sign-up';
import { User } from './dto/user';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  constructor(private httpClient: HttpClient, private router: Router) {}

  public login(username: String, password: String): void {
    this.httpClient
      .post('http://localhost:8080/auth/login', {
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
      'http://localhost:8080/auth/signup',
      newUser
    );
  }

  public userIsAuthenticated(): boolean {
    // TODO: Comprobar si el token está expirado
    return this.getToken() !== null;
  }

  public getToken(): string | null {
    return localStorage.getItem('token');
  }
}
