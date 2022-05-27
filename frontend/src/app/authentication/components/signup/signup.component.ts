import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { User } from '../../dto/user';
import { Observable } from 'rxjs';
import { SignUp } from '../../dto/sign-up';

import {
  Form,
  FormBuilder,
  FormGroup,
  Validators,
  NgForm,
} from '@angular/forms';
import { AuthenticationService } from '../../authentication.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss'],
})
export class SignupComponent implements OnInit {
  signupForm: FormGroup;

  constructor(
    private authenticationService: AuthenticationService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {
    this.signupForm = this.formBuilder.group({
      username: [
        '',
        Validators.compose([
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(20),
        ]),
      ],
      password: [
        '',
        Validators.compose([
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(20),
        ]),
      ],
      email: [
        '',
        Validators.compose([
          Validators.required,
          Validators.email,
          Validators.maxLength(40),
        ]),
      ],
    });
  }

  ngOnInit(): void {}

  signup() {
    this.authenticationService
      .signUp(this.signupForm.value as SignUp)
      .subscribe({
        next: (userOrError: User) => {
          // No ha habido errores, redirecciona a login
          this.router.navigate(['/login']);
        },
        error: (error) => {
          // Ha habido algún error
          console.log(error);
        },
      });
  }
}
