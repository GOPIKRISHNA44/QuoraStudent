import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot, CanActivate, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../services/authentication.service';
@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate {
    active:boolean=false
    constructor(private router: Router, private authenticationService: AuthenticationService) { }
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot):boolean {
         this.authenticationService.isLoggedIn().subscribe(res => {
            if (res.data.loggedout && res.success) {
                this.active=true;
            } else {
                this.router.navigate(['login']); 
                alert('Reason:' + res?.reason)
              }
            },
              error => {
                alert('Error occured with message ' + error?.message)
              })
              return this.active
          }     
           
  }    