
import { DatePipe } from '@angular/common';

import { Component, ElementRef, Inject, OnInit, ViewChild } from '@angular/core';

import { FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { appConstants } from '../constants/alert.constants';
import { Title, QuillConfiguration } from '../constants/title.constants';
import { UserDetails } from '../models/auth.model';
import { AlertService } from '../services/alert.service';
import { AuthenticationService } from '../services/authentication.service';
import { QuestionService } from '../services/question.service';
@Component({
  selector: 'app-ask-question-dialog',
  templateUrl: './ask-question-dialog.component.html',
  styleUrls: ['./ask-question-dialog.component.css']
})
export class AskQuestionDialogComponent implements OnInit {
  quillConfiguration = QuillConfiguration
  placeholder = Title.questionPlaceholder
  @ViewChild('quill') quill: ElementRef;
  editorText: string;
  userdetails: UserDetails;
  eventDTO = { "from": new Date(), "to": null }
  interests = []
  tags = new FormControl('');
  tagsList: any = []
   now = new Date();

  constructor(public dialogRef: MatDialogRef<AskQuestionDialogComponent>,private alertServc: AlertService,
    @Inject(MAT_DIALOG_DATA) public data: any, private questionService: QuestionService, private authenticationService: AuthenticationService,) { 

      // const datePipe = new DatePipe('');
      // this.now = datePipe.transform(new Date(), 'yyyy-MM-dd');
      this.now.setDate(this.now.getDate() + 1);

    }

  isQuestion: boolean = false;
  isEvent: boolean = false;
  ngOnInit(): void {
    this.userdetails = JSON.parse(this.authenticationService.GetUserDetails())
    this.questionService.getInterests().subscribe(res => {
      if (res.success) {
        this.interests = res.data.interests
      }
    })

    this.isQuestion = this.data.isQuestion;
    this.isEvent = this.data.isEvent;

    this.loadPlaceHolder();
  }

  getValues(event: {
    isUserInput: any;
    source: { value: any; selected: any };
  }) {
    if (event.isUserInput) {
      if (event.source.selected === true) {
        this.tagsList.push(event.source.value);
      } else {
        console.log(event.source.value)
        this.tagsList = this.tagsList.filter(data => data != event.source.value);
      }
    }
  }

  loadPlaceHolder() {
    this.placeholder = this.isQuestion ? Title.questionPlaceholder : (this.isEvent ? Title.eventPlaceholder : null)
  }
  submit(isQ, isEv) {
    if(this.editorText==null ||this.editorText.trim().length==0)
    {
      this.alertServc.successAlert("Can't be empty ");
      return ;
    }

    let sentText = {
      "userid": this.userdetails?.userid,
      "text": this.editorText,
      "tags": this.tagsList
    }

    if (isQ) {
      this.questionService.postQuestion(sentText).subscribe(res => {
        if (res.success) {
          this.alertServc.successAlert(appConstants.postQuestion);
        }
      })
    }
    if (isEv) {
      let payLoad = {
        "askAquestionDTO": sentText,
        "eventDTO": this.eventDTO
      }
      this.questionService.postEvent(payLoad).subscribe(res => {
        if (res.success) {
          this.alertServc.successAlert(appConstants.postEvent);
          
        }
      })
    }
    window.location.reload()
    this.dialogRef.close();
  }
  onNoClick() {
    this.dialogRef.close();
  }

}