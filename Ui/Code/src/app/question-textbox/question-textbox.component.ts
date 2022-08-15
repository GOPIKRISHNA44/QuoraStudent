import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AskQuestionDialogComponent } from '../ask-question-dialog/ask-question-dialog.component';
import { Title } from '../constants/title.constants';
import { UserDetails } from '../models/auth.model';
import { AuthenticationService } from '../services/authentication.service';
@Component({
  selector: 'app-question-textbox',
  templateUrl: './question-textbox.component.html',
  styleUrls: ['./question-textbox.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class QuestionTextboxComponent implements OnInit {
  title = Title.questionBox
  userdetails: UserDetails;
  constructor(public dialog: MatDialog,private router:Router,private authenticationService: AuthenticationService) { }
  ngOnInit(): void {
    this.userdetails = JSON.parse(this.authenticationService.GetUserDetails())
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

  openAskAnEvent() {
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
  openBlog(){
    this.router.navigate(['home/blog'])
  }
}
