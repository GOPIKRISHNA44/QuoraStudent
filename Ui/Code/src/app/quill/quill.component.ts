import { Component, Input, OnInit,Output, EventEmitter, Inject  } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { EditorChangeContent, EditorChangeSelection } from 'ngx-quill';
import { QuillConfiguration, Title } from '../constants/title.constants';
import { UserDetails } from '../models/auth.model';
import { AuthenticationService } from '../services/authentication.service';
import { QuestionService } from '../services/question.service';
@Component({
  selector: 'app-quill',
  templateUrl: './quill.component.html',
  styleUrls: ['./quill.component.css']
})
export class QuillComponent implements OnInit {
  quillConfiguration = QuillConfiguration
  placeholder = Title.questionPlaceholder
  editorText: string;
  userdetails: UserDetails;
  eventDTO = { "from": new Date(), "to": null }
  interests = []
  tags = new FormControl('');
  tagsList: any = []
  isQuestion: boolean = false;
  isEvent: boolean = false;
  tagsId = []
  selected=[]
  constructor(public dialogRef: MatDialogRef<QuillComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private questionService: QuestionService, private authenticationService: AuthenticationService,) { }

  ngOnInit(): void {
    this.editorText=this.data?.data.question
    this.userdetails = JSON.parse(this.authenticationService.GetUserDetails())
    this.eventDTO.from=this.data?.data.ctype=='E'? new Date(this.data?.data.fromdate):new Date()
    this.eventDTO.to=this.data?.data.ctype=='E'? new Date(this.data?.data.todate):null
    this.questionService.getInterests().subscribe(res => {
      if (res.success) {
        this.interests = res.data.interests
        this.tagsId = this.data?.data?.tags?.split(';').filter((a) => a)
        this.tagsId?.filter((id) => {
         this.interests?.map(item => {
             if(parseInt(id) == item.id){
              this.selected.push(item.id)
             }
          })
        })
      }
    })
    this.tagsList=this.selected
    this.isQuestion = this.data?.isQuestion;
    this.isEvent = this.data?.isEvent;
  //  this.tagsList=this.data?.data.tags
    this.loadPlaceHolder();
  }
  blured = false
  focused = false

  created(event) {
    // tslint:disable-next-line:no-console
    console.log('editor-created', event)
  }

  changedEditor(event: EditorChangeContent | EditorChangeSelection) {
    // tslint:disable-next-line:no-console
    console.log('editor-change', event)
  }

  focus($event) {
    // tslint:disable-next-line:no-console
    console.log('focus', $event)
    this.focused = true
    this.blured = false
  }

  blur($event) {
    // tslint:disable-next-line:no-console
    console.log('blur', $event)
    this.focused = false
    this.blured = true
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
      "eqid": this.data?.data.eqid,
      "text": this.editorText,
      "tags": this.tagsList
    }

    if (isQ) {
      this.questionService.editUpdateQuestion(sentText).subscribe(res => {
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
      this.questionService.updateAnEvent(payLoad).subscribe(res => {
        if (res.success) {
          console.log(res)
          
        }
      })
    }
    this.dialogRef.close({reload:true,ctype:this.data?.data.ctype});
  }
  onNoClick() {
    this.dialogRef.close();
  }
}
