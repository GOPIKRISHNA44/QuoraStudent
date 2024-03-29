import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot, CanActivate, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { pathsInfo } from '../constants/path.contants';
import { AuthenticationService } from '../services/authentication.service';
@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  active: boolean = false
  constructor(private router: Router, private authenticationService: AuthenticationService) { }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    const currentPath = route.data.path;
    return this.authenticationService.isLoggedIn().map(res => {
      if (res.data.valid) {

        if (pathsInfo.AuthRoutes.includes(currentPath)) {
          return true;
        }
        else {
          this.router.navigate(['home']);
          return true;
        }


      } else {

        if (pathsInfo.UnAuthRoutes.includes(currentPath)) {
          return true;
        }
        else {
          this.router.navigate(['login']);
          return true;
        }


      }
    },
      error => {
        alert('Error occured with message ' + error?.message)
      })
  }

}    