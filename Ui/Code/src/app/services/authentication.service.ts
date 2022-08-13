import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs';
import { environment } from 'src/environments/environment';
import { LoginDetails, SignUpDetails } from '../models/auth.model';
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

  private _loading = new BehaviorSubject<boolean>(false);
  public readonly loading$ = this._loading.asObservable();


  login(loginDetails): Observable<any> {
    return this.http.post<LoginDetails>(this.loginURL, loginDetails)
  }

  logout(): Observable<any> {
    let sessionkey = this.GetToken();
    return this.http.post(this.logoutURL, { sessionkey: sessionkey })
  }
  isLoggedIn(): Observable<any> {
    let sessionKey = this.GetToken();
    return this.http.post(this.checkSessionURL, { sessionkey: sessionKey })
  }
  GetToken() {
    return localStorage.getItem('token') || '';
  }
  signUp(signUpDetails): Observable<any> {
    return this.http.post<SignUpDetails>(this.registrationURL, signUpDetails)
  }

  GetUserDetails() {
    return localStorage.getItem('userdetails') ;
  }

  show() {
    this._loading.next(true);
  }

  hide() {
    this._loading.next(false);
  }
  // setUserDetails(userDetails) {
  //   this.userDetails$.next(userDetails)
  // }

  
  getUniversityList(url):Observable<any>
  {
    return this.http.get(url);
  }

}
