import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AskQuestionDialogComponent } from '../ask-question-dialog/ask-question-dialog.component';
@Component({
  selector: 'app-question-textbox',
  templateUrl: './question-textbox.component.html',
  styleUrls: ['./question-textbox.component.css']
})
export class QuestionTextboxComponent implements OnInit {

  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {
  }
  openAskQuestion(){
    const dialogRef = this.dialog.open(AskQuestionDialogComponent, {
      width: '75%',
      height:'82vh'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        console.log(result)
      }
    });

  }
}
