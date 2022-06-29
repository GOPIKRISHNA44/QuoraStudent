import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot, CanActivate, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../services/authentication.service';
@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  active: boolean = false
  constructor(private router: Router, private authenticationService: AuthenticationService) { }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean>  {
    return this.authenticationService.isLoggedIn().map( res => {
      if (res.data.valid) {
        return true;
      } else {
        this.router.navigate(['login']);
        return false;
      }
    },
      error => {
        alert('Error occured with message ' + error?.message)
      })
  }

}    