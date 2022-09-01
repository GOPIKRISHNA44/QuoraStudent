import { Component, ElementRef, Inject, OnInit, ViewChild } from '@angular/core';
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
  placeholder = Title.questionPlaceholder
  @ViewChild('quill') quill: ElementRef;
  editorText: string;
  userdetails: UserDetails;
  eventDTO = { "from": new Date(), "to": null }
  interests = []
  tags = new FormControl('');
  tagsList: any = []

  constructor(public dialogRef: MatDialogRef<AskQuestionDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private questionService: QuestionService, private authenticationService: AuthenticationService,) { }

  isQuestion: boolean = false;
  isEvent: boolean = false;
  ngOnInit(): void {
    this.userdetails = JSON.parse(this.authenticationService.GetUserDetails())
    this.questionService.getInterests().subscribe(res => {
      if (res.success) {
        this.interests = res.data.interests
      }
    })
    console.log("hi " + this.data.isQuestion);
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
    let sentText = {
      "userid": this.userdetails?.userid,
      "text": this.editorText,
      "tags": this.tagsList
    }

    if (isQ) {
      this.questionService.postQuestion(sentText).subscribe(res => {
        if (res.success) {
          console.log(res)
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
          console.log(res)
          
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