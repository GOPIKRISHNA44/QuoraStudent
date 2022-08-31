import { Component, HostListener, OnInit, ViewChild } from '@angular/core';
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
import { QuestionService } from '../services/question.service';

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
  ctype: string = "Q"
  rightSideData: any;
  tags = []
  rightSideTitle = "TOP RATED QUESTIONS"
  constructor(private questionService: QuestionService, public dialog: MatDialog, private observer: BreakpointObserver,
    private router: Router, private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    let userdetails = this.authenticationService.GetUserDetails();
    this.userdetails = JSON.parse(this.authenticationService.GetUserDetails())
    if (userdetails) {
      userdetails = JSON.parse(userdetails);
      this.authenticationService.getInterestPopupStatus(userdetails["userid"]).subscribe((data) => {
        if (data) {
          if (data["data"]["status"] == 0) {
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
      })
    }
    this.tags = []
    this.rightSideView()

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
        break
      case 'Blog':
        this.router.navigate(['home/blog'])
        break
      case 'Leaderboard':
        this.router.navigate(['home/leaderboard'])
        break
      case 'Events':
        this.openEvents()
        break
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
  openEvents() {
    const dialogRef = this.dialog.open(AskQuestionDialogComponent, {
      width: '60%',
      data: {
        isQuestion: false,
        isEvent: true
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
      }
    });
  }
  myQuestionsOrEvents() {
    this.rightSideView()
    this.router.navigate(['/home/myQuestionOrEvents/'])

  }
  myBlogs() {
    this.rightSideView()
    this.router.navigate(['/home/myBlogs/'])
  }
  rightSideView() {
    this.questionService.requiredCtype$.subscribe((value) => {
      if (value && value != {}) {
        this.ctype = value;
      }
      else {
        this.ctype = 'Q'
        this.rightSideTitle = "TOP RATED QUESTIONS"
      }
    });
    if (this.ctype == 'B') {
      this.rightSideTitle = "TOP BLOGS"
      let details = {
        "userid": this.userdetails.userid
      }
      this.questionService.getTopLikedBlogs(details).subscribe(res => {
        if (res.success) {
          this.rightSideData = res.data
        }
      })
    }
    else {
      let details = {
        "userid": this.userdetails.userid,
        "tags": this.tags,
        "ctype": this.ctype
      }
      if (this.ctype == 'Q') {
        this.rightSideTitle = "TOP RATED QUESTIONS"
      }
      else {
        this.rightSideTitle = "TOP RATED EVENTS"
      }
      this.questionService.getTagRelatedQuesOrEvents(details).subscribe(res => {
        if (res.success) {
          this.rightSideData = res.data
        }
      })
    }


  }
  openQuestion(data) {
    if (data?.ctype == 'Q') {
      this.rightSideTitle = "RELATED QUESTIONS"
    }
    else {
      this.rightSideTitle = "RELATED EVENTS"
    }
    this.router.navigate(['home/question/',], { queryParams: { 'eqid': data?.eqid, 'ctype': data?.ctype } })
  }
  openBlog(data) {
    this.rightSideTitle = "RELATED BLOGS"
    this.questionService.setBlogDetails(data)
    this.router.navigate(['home/viewBlog/'], { queryParams: { 'bid': data?.bid, 'ctype': 'B' } })
  }

  tagsRelatedSide(tags) {

    this.tags = [tags.split(';').join(',')][0] != ',,' ? [tags.split(';').join(',')][0] : []
    this.rightSideView()

  }
  
}
