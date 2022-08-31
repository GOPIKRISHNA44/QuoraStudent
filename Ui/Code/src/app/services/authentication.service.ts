import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs';
import { environment } from 'src/environments/environment';
import { LoginDetails, SignUpDetails } from '../models/auth.model';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { SpinnerService } from './spinner.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {


  private registrationURL: string = environment.apiEndPoint + environment.user.endPoint + environment.user.register;
  private loginURL: string = environment.apiEndPoint + environment.user.endPoint + environment.user.login;
  private checkSessionURL = environment.apiEndPoint + environment.user.endPoint + environment.user.checkSession;
  private logoutURL = environment.apiEndPoint + environment.user.endPoint + environment.user.logout;
  private interestPopupStatusURL = environment.apiEndPoint + environment.user.endPoint + environment.user.getInterestpopupStatus;
  private getNotifApi: any = environment.apiEndPoint + environment.getNotifApi;
  private delNotifApi: any = environment.apiEndPoint + environment.deleteNotifsApi;
  private unvlistApi: any = environment.apiEndPoint + environment.unvlist;
  private resetPasswordApi: any = environment.apiEndPoint + environment.resetPassword;
  private validateResetPasswordApi: any = environment.apiEndPoint + environment.validateResetPassword;
  private updatePasswordApi: any = environment.apiEndPoint + environment.updatePassword;

  constructor(private http: HttpClient, private spinnerService: SpinnerService) { }

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
    return localStorage.getItem('userdetails');
  }

  show() {
    if (this.spinnerService.getLoaderStatus()){
      this._loading.next(true);
    }
 
  }

  hide() {
    this._loading.next(false);
  }
  // setUserDetails(userDetails) {
  //   this.userDetails$.next(userDetails)
  // }


  getUniversityList(): Observable<any> {
    return this.http.get(this.unvlistApi);
  }


  getInterestPopupStatus(userid: any): Observable<any> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("userid", userid);
    return this.http.get(this.interestPopupStatusURL, { params: queryParams });
  }

  getNotifications(userid: any): Observable<any> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("userid", userid);
    return this.http.get(this.getNotifApi, { params: queryParams });
  }

  deleteNotifications(userid, ids): Observable<any> {
    return this.http.post(this.delNotifApi, { ids: ids, userid: userid })
  }

  resetPassword(emailid): Observable<any> {
    return this.http.post(this.resetPasswordApi, { emailid: emailid })
  }

  validateResetPassword(e,q): Observable<any> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("e", e);
    queryParams = queryParams.append("q", q);
    return this.http.get(this.validateResetPasswordApi, { params: queryParams })
  }

  updatePassword(u,p): Observable<any> {
    return this.http.post(this.updatePasswordApi, { "userid" : u, "password": p  })
  }
}
