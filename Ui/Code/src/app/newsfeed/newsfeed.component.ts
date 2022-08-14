import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserDetails } from '../models/auth.model';
import { AuthenticationService } from '../services/authentication.service';
import { QuestionService } from '../services/question.service';

@Component({
  selector: 'app-newsfeed',
  templateUrl: './newsfeed.component.html',
  styleUrls: ['./newsfeed.component.css']
})
export class NewsfeedComponent implements OnInit {
  userdetails: UserDetails;
  data: any;
  commentsData: any;
  showComments: boolean=false;
  comment:string=''
  constructor(private router: Router,private questionService: QuestionService, private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    this.userdetails = JSON.parse(this.authenticationService.GetUserDetails())

    this.questionService.getNewsFeed(this.userdetails.universitycode).subscribe(res => {
      if (res.success) {
        this.data = res.data.map(item=>{
          return {...item, showComments:false}
      })
      }
    })

  }
  likeButton(){
    this.data.upvotes++
  }
  openQuestion(data){
    let questionDetails={
      "eqid":data?.eqid,
      "ctype":data?.ctype,
      "userid":data?.userid,
    }
    this.router.navigate(['home/question/'],{queryParams:{'eqid':data?.eqid,'ctype':data?.ctype}})
      
  }
  openComments(eqid,ctype){
    let commentDetails = {
      "requestingUserId": this.userdetails.userid,
      "ctype": ctype,
      "eqabcid":eqid
    }
    this.questionService.getComments(commentDetails).subscribe(response => {
      if (response) {
        this.commentsData = response
        this.data.map(item=>{
          if(item.eqid==eqid){
            item.showComments=true
          }
          else{
            item.showComments=false
          }
      })
      }  
    })
  }
  sendComment(questionData){
    let sendCommentDetails = {
      "userid": this.userdetails.userid,
      "parentid": questionData.eqid,
      "ctype": questionData.ctype,
      "comment": this.comment
    }
    this.questionService.sendComments(sendCommentDetails).subscribe(response => {
      if (response) {
        this.comment = ''
        this.openComments(questionData.eqid,questionData.ctype)
      }
    })
  }
  deleteQuestionComment(questionData){
    this.questionService.deleteComment({"cid":questionData?.cid}).subscribe(response => {
      if (response) {
        this.openComments(questionData.parentid,questionData.ctype)
      }
    })
  }
}
