import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import {
  Form,
  FormBuilder,
  FormGroup,
  Validators,
  NgForm,
} from '@angular/forms';
import { AuthenticationService } from '../../authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(
    private authenticationService: AuthenticationService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    // Comprobamos si el token está validado
    let tokenStatus: Boolean = this.authenticationService.userIsAuthenticated();
    // Si es válido, redirige a Home
    if (tokenStatus) {
      this.router.navigate(['/']);
    }
  }

  login() {
    this.authenticationService.login(
      this.loginForm.value.username,
      this.loginForm.value.password
    );
  }
}
