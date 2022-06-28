import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map'
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private registrationURL: string = environment.apiEndPoint + environment.user.endPoint + environment.user.register;
  private loginURL: string = environment.apiEndPoint + environment.user.endPoint + environment.user.login;
  private checkSessionURL = environment.apiEndPoint + environment.user.endPoint + environment.user.checkSession;
  private logoutURL = environment.apiEndPoint + environment.user.endPoint + environment.user.logout;



  constructor(private http: Http) { }
  login(username: string, password: string) {
    return this.http.post(this.loginURL, JSON.stringify({ username: username, password: password }))
      .map((response: Response) => {
        // login successful if there's a jwt token in the response
        let user = response.json();
        if (user && user.token) {
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify(user));
        }
      });
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
  }
}
