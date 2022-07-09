import { Component, OnInit ,ViewChild} from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';
import { MatSidenav } from '@angular/material/sidenav';
import { BreakpointObserver } from '@angular/cdk/layout';
import { delay, filter } from 'rxjs/operators';
import { MatDialog } from '@angular/material/dialog';
import { Title,sideNavItems,toolbarIcons } from '../constants/title.constants';
import { InterestsDialogComponent } from '../interests-dialog/interests-dialog.component';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  mainTitle=Title.mainTitle;
  sideNavItemsNames=sideNavItems;
  toolbarIconsItems=toolbarIcons;
  @ViewChild(MatSidenav)
  sidenav!: MatSidenav;
  constructor( public dialog: MatDialog,private route: ActivatedRoute,private observer: BreakpointObserver,
    private router: Router, private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    const dialogRef = this.dialog.open(InterestsDialogComponent, {
      width: '600px',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        console.log(result)
      }
    });
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
  sideItemNavigation(sideNavItemsName){
    switch(sideNavItemsName){
      case 'log':
        return

      default:
        return
    }

  }
  toolbarIcons(toolbarIcon){
    switch(toolbarIcon.icon){
      case 'account_circle':
        this.logOut();

      default:
        return
    }
  }
}
