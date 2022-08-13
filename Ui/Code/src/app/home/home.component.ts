import { Component, OnInit, ViewChild } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';
import { MatSidenav } from '@angular/material/sidenav';
import { BreakpointObserver } from '@angular/cdk/layout';
import { delay, filter } from 'rxjs/operators';
import { MatDialog } from '@angular/material/dialog';
import { Title, sideNavItems, toolbarIcons } from '../constants/title.constants';
import { InterestsDialogComponent } from '../interests-dialog/interests-dialog.component';
import { AskQuestionDialogComponent } from '../ask-question-dialog/ask-question-dialog.component';
import { UserDetails } from '../models/auth.model';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  mainTitle = Title.mainTitle;
  sideNavItemsNames = sideNavItems;
  toolbarIconsItems = toolbarIcons;
  @ViewChild(MatSidenav) sidenav!: MatSidenav;
  userdetails: UserDetails;
  constructor(public dialog: MatDialog, private observer: BreakpointObserver,
    private router: Router, private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    if(this.userdetails && this.userdetails["interestspopup"]==0){
    const dialogRef = this.dialog.open(InterestsDialogComponent, {
      width: '600px',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        console.log(result)
      }
    });
  }
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
      });
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
  sideItemNavigation(sideNavItemsName) {
    switch (sideNavItemsName) {
      case 'Question':
        this.openAskQuestion();
      case 'Blog':
        this.router.navigate(['home/blog'])
      case 'Leaderboard':
        return
      case 'Events':
        return
      default:
        return
    }

  }
  openAskQuestion() {
    const dialogRef = this.dialog.open(AskQuestionDialogComponent, {
      width: '60%',
      data: {

        isQuestion: true,
        isEvent: false

      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
      }
    });

  }
  toolbarIcons(toolbarIcon) {
    switch (toolbarIcon.icon) {
      case 'account_circle':
        this.logOut();

      default:
        return
    }
  }
}
