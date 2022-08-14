import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AskQuestionDialogComponent } from '../ask-question-dialog/ask-question-dialog.component';
import { Title } from '../constants/title.constants';
@Component({
  selector: 'app-question-textbox',
  templateUrl: './question-textbox.component.html',
  styleUrls: ['./question-textbox.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class QuestionTextboxComponent implements OnInit {
  title = Title.questionBox
  constructor(public dialog: MatDialog,private router:Router) { }
  ngOnInit(): void {
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
