import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map'
import { environment } from 'src/environments/environment';
import { LoginDetails } from '../models/auth.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private registrationURL: string = environment.apiEndPoint + environment.user.endPoint + environment.user.register;
  private loginURL: string = environment.apiEndPoint + environment.user.endPoint + environment.user.login;
  private checkSessionURL = environment.apiEndPoint + environment.user.endPoint + environment.user.checkSession;
  private logoutURL = environment.apiEndPoint + environment.user.endPoint + environment.user.logout;


  constructor(private http: HttpClient) { }
  login(loginDetails): Observable<any> {
    return this.http.post<LoginDetails>(this.loginURL, loginDetails)
  }

  logout(): Observable<any> {     
    let sessionkey=localStorage.getItem('token')   
    return this.http.post(this.logoutURL,{sessionkey:sessionkey})   
    }    
    isLoggedIn(): Observable<any>{
      let sessionKey=localStorage?.getItem('token')||''
      return this.http.post(this.checkSessionURL,{sessionkey:sessionKey})
    }
    GetToken(){
      return localStorage.getItem('token')||'';
     }
}
