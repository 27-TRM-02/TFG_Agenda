import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(
    private authenticationService: AuthenticationService,
    private router: Router
  ) {}
  canActivate(): boolean {
    let canActivate = true;
    if (!this.authenticationService.userIsAuthenticated()) {
      canActivate = false;
      // Ha habido algún error, redirige a login
      this.router.navigate(['/login']);
    }
    return canActivate;
  }
}
