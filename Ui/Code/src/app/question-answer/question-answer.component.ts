import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
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
  isliked=true;
  eqid:string;
  ctype:string
  constructor(private questionService: QuestionService,private authenticationService:AuthenticationService,private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.userdetails=JSON.parse(this.authenticationService.GetUserDetails())
    this.eqid= this.route.snapshot.queryParams['eqid']
    this.ctype= this.route.snapshot.queryParams['ctype']
    let details={
      "eqid":this.eqid,
      "ctype":this.ctype,
      "userid":this.userdetails.userid
    }
    this.questionService.getQuestionDetails(details).subscribe(res => {
      if (res.success) {
        this.questionData=res.data
        //this.questionService.updateQuestion(res?.data)
        console.log(res)
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
      "parentid":this.questionData.eqid,
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
