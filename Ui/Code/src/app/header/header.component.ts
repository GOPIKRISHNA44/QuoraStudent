import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { sideNavItems, Title, toolbarIcons } from '../constants/title.constants';
import { BreakpointObserver } from '@angular/cdk/layout';
import { delay, filter } from 'rxjs/operators';
import { NavigationEnd, Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  mainTitle=Title.mainTitle;
  sideNavItemsNames=sideNavItems;
  toolbarIconsItems=toolbarIcons;
  @Input() sidenav!: MatSidenav;
  userdetails = JSON.parse(this.authenticationService.GetUserDetails());
  constructor(private observer: BreakpointObserver,
    private router: Router, private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
  }
  ngAfterViewInit() {
    this.observer
      .observe(['(max-width: 800px)'])
      .pipe(delay(1))
      .subscribe((res) => {
        if (res.matches) {
          this.sidenav.mode = 'over';
          this.sidenav.close();
        } else {
          this.sidenav.mode = 'side';
          this.sidenav.open();
        }
      });
      this.router.events
      .pipe(
        filter((e) => e instanceof NavigationEnd)
      )
      .subscribe(() => {
        if (this.sidenav.mode === 'over') {
          this.sidenav.close();
        }
      });}
      toolbarIcons(toolbarIcon){
        switch(toolbarIcon.icon){
          case 'account_circle':
            this.logOut();
    
          default:
            return
        }
      }
      logOut() {
        this.authenticationService.logout().subscribe(res => {
          if (res.success) {
            localStorage.removeItem('token');
            localStorage.removeItem('userdetails');
            this.router.navigate(['/login'])
          } else {
            alert('Reason:home' + res?.reason)
          }
        },
          error => {
            alert('Error occured with message ' + error?.message)
          })
      }
}
