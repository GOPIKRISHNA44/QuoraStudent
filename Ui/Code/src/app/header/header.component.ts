import { Component, Input, OnInit, ViewChild, ViewEncapsulation } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { sideNavItems, Title, toolbarIcons } from '../constants/title.constants';
import { BreakpointObserver } from '@angular/cdk/layout';
import { delay, distinctUntilChanged, filter, takeWhile } from 'rxjs/operators';
import { NavigationEnd, Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';
import { environment } from 'src/environments/environment';
import { AlertService } from '../services/alert.service';
import { appConstants } from '../constants/alert.constants';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/mergeMap';
import 'rxjs/add/observable/interval';
import { ThrowStmt } from '@angular/compiler';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class HeaderComponent implements OnInit {
  mainTitle = Title.mainTitle;
  sideNavItemsNames = sideNavItems;
  toolbarIconsItems = toolbarIcons;
  @Input() sidenav!: MatSidenav;

  unvTitle: any = "";
  userdetails = JSON.parse(this.authenticationService.GetUserDetails());

  notifViewCount: any = 0;
  deletedNotifIds = [];
  notificationsList: any = [];
  displayNotifComponent: boolean = true;
  timer: any = null;
  sub: any;

  constructor(private observer: BreakpointObserver,
    private router: Router, private authenticationService: AuthenticationService, private alertServc: AlertService) { }

  ngOnInit(): void {

    this.authenticationService.getUniversityList()
      .subscribe((data) => {

        if (data && data.success) {
          this.unvTitle = data["data"]["univ"][this.userdetails["universitycode"]]["unvname"]
        }

      })



    this.doNotifApiCall();
    

  }
  itemHeightFn(item, index) {return 182;}

 

  doNotifApiCall() {
    this.authenticationService.getNotifications(this.userdetails["userid"])
      .subscribe((data) => {
        if (data && data.success) {
          let newList = data["data"];
          let updatedList = [];
          let existingIds = this.notificationsList.map((x) => { return x["id"] });
          newList.forEach(element => {
            if (!(existingIds.includes(element["id"]))) {
              updatedList.push(element);
            }
          });
          if (updatedList.length > 0) {
            this.notifViewCount += updatedList.length;
            updatedList.push(...this.notificationsList)
            this.notificationsList = updatedList;
          }

        }
      })
    //  this.wait(3000);
  //   this.doNotifApiCall();
  }

  notificationsSeen() {
    if (this.notifViewCount > 0) {
      this.notifViewCount = 0;
      let deleteableIds = [];
      this.notificationsList.forEach(element => {
        if (!(this.deletedNotifIds.includes(element["id"]))) {
          deleteableIds.push(element["id"]);
        }
        
      });
      this.authenticationService.deleteNotifications(this.userdetails["userid"],deleteableIds).subscribe((data) => {
        console.log(data);
      })

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
  toolbarIcons(toolbarIcon) {
    switch (toolbarIcon.icon) {
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
      this.alertServc.successAlert(appConstants.loggedOut);
    },
      error => {
        alert('Error occured with message ' + error?.message)
      })
  }

  gotoHome() {
    this.router.navigate(['/home'])
  }
  myQuestions(){
    
  }
}
