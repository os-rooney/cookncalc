import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credentials = {username: '', password: ''}

  loginError?: string;

  constructor() { }

  ngOnInit(): void {
  }

  valid() {
    return this.credentials && this.credentials.username.length !== 0 && this.credentials.password.length !== 0;
  }

  login() {
    if (this.valid()) {
      // TODO: login
    }
  }

}
