import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Title } from '../constants/title.constants';
import { AuthenticationService } from '../services/authentication.service';
import { QuestionService } from '../services/question.service';

@Component({
  selector: 'app-interests-dialog',
  templateUrl: './interests-dialog.component.html',
  styleUrls: ['./interests-dialog.component.css']
})
export class InterestsDialogComponent implements OnInit {
interests=[]
  constructor(public dialogRef: MatDialogRef<InterestsDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private questionService: QuestionService) { }

  title = Title.interestsTitle

  ngOnInit(): void {
    this.questionService.getInterests().subscribe(res => {
      if (res.success) {
        this.interests=res.data.interests
      }
    })
  }
  onNoClick() {
    this.dialogRef.close();
  }
  submit(){
    
  }
}
