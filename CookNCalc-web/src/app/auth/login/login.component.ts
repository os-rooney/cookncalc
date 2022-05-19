import { Component, OnInit } from '@angular/core';
import {AuthService} from "../auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credentials = {username: '', password: ''}

  loginError?: string;

  constructor(private authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
  }

  valid() {
    return this.credentials && this.credentials.username.length !== 0 && this.credentials.password.length !== 0;
  }

  login() {
    if (this.valid()) {
      this.authService.authenticate(this.credentials.username, this.credentials.password,
        () => this.router.navigate(['/']),
        (err: string | undefined) => this.loginError = err);
    }
  }
}
