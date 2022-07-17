import { Component, Inject, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Title, QuillConfiguration } from '../constants/title.constants';
import { UserDetails } from '../models/auth.model';
import { AuthenticationService } from '../services/authentication.service';
import { QuestionService } from '../services/question.service';
@Component({
  selector: 'app-ask-question-dialog',
  templateUrl: './ask-question-dialog.component.html',
  styleUrls: ['./ask-question-dialog.component.css']
})
export class AskQuestionDialogComponent implements OnInit {
  quillConfiguration = QuillConfiguration
  placeholder = Title.questionPlaceHolder
  editorText: string;
  userdetails:UserDetails ;
  interests = []
  tags = new FormControl('');
  
  constructor(public dialogRef: MatDialogRef<AskQuestionDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private questionService: QuestionService, private authenticationService: AuthenticationService,) { }

  ngOnInit(): void {
    this.userdetails=JSON.parse(this.authenticationService.GetUserDetails())
    this.questionService.getInterests().subscribe(res => {
      if (res.success) {
        this.interests = res.data.interests
      }
    })
  }
  submit() {
    let sentText={
      "userid":this.userdetails?.userid,
      "text":this.editorText,
      "tags":[1,2,3]
    } 
    this.questionService.postQuestion(sentText).subscribe(res => {
      if (res.success) {
        console.log(res)
      }
    })
    this.dialogRef.close();

  }
  onNoClick() {
    this.dialogRef.close();
  }
}
