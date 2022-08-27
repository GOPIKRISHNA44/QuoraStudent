import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { QuillConfiguration, Title } from '../constants/title.constants';
import { UserDetails } from '../models/auth.model';
import { QuestionService } from '../services/question.service';

@Component({
  selector: 'app-answer-dialog',
  templateUrl: './answer-dialog.component.html',
  styleUrls: ['./answer-dialog.component.css']
})
export class AnswerDialogComponent implements OnInit {
  quillConfiguration = QuillConfiguration
  editorText: string;
  placeholder = Title.answerPlaceholder
  userdetails:UserDetails ;
  question:string;
  details: any;
  constructor(public dialogRef: MatDialogRef<AnswerDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,private questionService:QuestionService) { }

  ngOnInit(): void {
    this.question=this.data.question
    this.details=this.data.details
    
  }
  onNoClick() {
    this.dialogRef.close();
  }
  submit() {
    let answerDetail={
      "eqid":this.details.eqid,
      "ctype":this.details.ctype,
      "content":this.editorText,
      "userid":this.details.userid
    }
    this.questionService.postAnswer(answerDetail).subscribe(res => {
      if (res.success) {
        this.dialogRef.close(res.success);
      }
    })
  }

}
