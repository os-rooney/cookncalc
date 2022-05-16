import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from './model/user';

const SECURITY_EXAMPLE_LOGIN_STATE = 'SECURITY_EXAMPLE_LOGIN_STATE';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private user?: User;
  private authenticated = false;

  constructor(private http: HttpClient) {
    if (AuthService.checkLoginState()) {
      this.refreshSession();
    }
  }

  private static saveLoginState(user: User) {
    const data = {user: user.username, login: Date.now()}
    localStorage.setItem(SECURITY_EXAMPLE_LOGIN_STATE, JSON.stringify(data));
  }

  private static checkLoginState(): { user: string, login: number } | null {
    const data = localStorage.getItem(SECURITY_EXAMPLE_LOGIN_STATE);
    if (data) {
      return JSON.parse(data);
    }
    return null;
  }

  public authenticate(username: string, password: string, successCallback?: Function, errorCallback?: Function) {
    this.http.post<User>('/api/auth/login', {username, password}).subscribe({
        next: user => {
          this.user = user;
          this.authenticated = true;
          AuthService.saveLoginState(user);
          if (successCallback) {
            successCallback(user);
          }
        },
        error: err => {
          console.error('Login failed!', err);
          if (errorCallback) {
            errorCallback(err);
          }
        }
      }
    )
  }

  private refreshSession() {
    this.http.get<User>('/api/users/current').subscribe(user => {
        if (user) {
          this.user = user;
          this.authenticated = true;
          AuthService.saveLoginState(user);
        } else {
          this.user = undefined;
          this.authenticated = false;
          localStorage.removeItem(SECURITY_EXAMPLE_LOGIN_STATE);
        }
      }
    )
  }

  public logout() {
    this.http.post('/api/auth/logout', {}).subscribe(() => console.log('logout successful'))
    this.user = undefined;
    this.authenticated = false;
    localStorage.removeItem(SECURITY_EXAMPLE_LOGIN_STATE);
  }

  get currentUser() {
    return this.user;
  }

  get isAuthenticated() {
    return this.authenticated;
  }

  get isAdmin() {
    return this.user?.admin ?? false;
  }


}
