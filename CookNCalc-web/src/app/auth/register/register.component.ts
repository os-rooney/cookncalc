import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {User} from "../../model/user";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registration = {username: '', password: '', passwordRepeat: ''}

  constructor(private http: HttpClient, private router: Router) {
  }

  ngOnInit(): void {
  }

  valid() {
    return this.registration &&
      (this.registration.username.length !== 0
        && this.registration.password.length !== 0
        && this.registration.passwordRepeat.length !== 0)
      && (this.registration.password === this.registration.passwordRepeat);
  }

  register() {
    if (this.valid()) {
      this.http.post<User>('/api/auth/register', this.registration).subscribe(user => {
        this.registration = {username: '', password: '', passwordRepeat: ''}
        this.router.navigate(['/login']);
      });
    }
  }
}
