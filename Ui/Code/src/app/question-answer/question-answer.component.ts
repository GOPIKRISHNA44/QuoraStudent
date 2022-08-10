import { Component, OnInit } from '@angular/core';
import { UserDetails } from '../models/auth.model';
import { AuthenticationService } from '../services/authentication.service';
import { QuestionService } from '../services/question.service';

@Component({
  selector: 'app-question-answer',
  templateUrl: './question-answer.component.html',
  styleUrls: ['./question-answer.component.css']
})
export class QuestionAnswerComponent implements OnInit {
  questionData: any
  answerData:any
  commentsData:any
  userdetails:UserDetails;
  showComments=false;
  comment:string='';
  constructor(private questionService: QuestionService,private authenticationService:AuthenticationService) { }

  ngOnInit(): void {
    this.userdetails=JSON.parse(this.authenticationService.GetUserDetails())
    this.questionService.questiondetails$.subscribe(res => {
      if (res) {
        this.questionData = res
        let details={
          "eqid":this.questionData.eqid,
          "ctype":this.questionData.ctype,
          "userid":this.questionData.userid
        }
        this.questionService.getAnswers(details).subscribe(response => {
          if (response) {
            this.answerData=response
          }
        
        })

      }
    })
  }
  openQuestion() {
    
  }
  openComments(){
    let commentDetails={
      "requestingUserId":this.userdetails.userid,
      "ctype":this.questionData.ctype,
      "eqabcid":this.questionData.eqid
    }
    this.questionService.getComments(commentDetails).subscribe(response => {
      if (response) {
        this.commentsData=response
        this.showComments=true
      }
    
    })
  }
  sendComment(){
    let sendCommentDetails={
      "userid":this.userdetails.userid,
      "parentid":this.questionData.userid,
      "ctype":this.questionData.ctype,
      "comment":this.comment
    }
    this.questionService.sendComments(sendCommentDetails).subscribe(response => {
      if (response) {
        this.comment=''
        this.openComments()
      }   
    })
  }
}
