import { Component, HostListener, OnInit } from '@angular/core';
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
  @HostListener("window:scroll", ["$event"])
onWindowScroll() {
//In chrome and some browser scroll is given to body tag
let pos = (document.documentElement.scrollTop || document.body.scrollTop) + document.documentElement.offsetHeight;
let max = document.documentElement.scrollHeight;
// pos/max will give you the distance between scroll bottom and and bottom of screen in percentage.
 if(pos == max )   {
 //Do your action here
 }
 console.log('hi')
}
  userdetails: UserDetails;
  data: any;
  commentsData: any;
  showComments: boolean=false;
  comment:string='';
  pageNumber=1
  noOfposts=6
  searchText=""
  toggleValue="Q"
  tempdata: any;
  answer=''
  constructor(private router: Router,private questionService: QuestionService, private authenticationService: AuthenticationService) { }


  ngOnInit(): void {
    this.getQuestions()

  }
  getQuestions(){
    this.userdetails = JSON.parse(this.authenticationService.GetUserDetails())
    let details={
        "ctype":this.toggleValue,
        "userid":this.userdetails.userid,
        "pageNumber":this.pageNumber,
        "numberOfPostsRequired":this.noOfposts,
        "filterCondition":this.searchText
    }
    this.questionService.getQuestionOrEventFeed(details).subscribe(res => {
      if (res.success) {
        this.data = res.data.data.map(item=>{
          return {...item, showComments:false,showAnswer:false}
      })
      }
    })
  }
 
  openQuestion(data){
    let questionDetails={
      "eqid":data?.eqid,
      "ctype":data?.ctype,
      "userid":data?.userid,
    }
    this.router.navigate(['home/question/'],{queryParams:{'eqid':data?.eqid,'ctype':data?.ctype}})
      
  }
  openComments(data){
    if(!data.showComments){
      let commentDetails = {
        "requestingUserId": this.userdetails.userid,
        "ctype": data?.ctype,
        "eqabcid":data?.eqid
      }
      this.questionService.getComments(commentDetails).subscribe(response => {
        if (response) {
          this.commentsData = response
          this.data.map(item=>{
            if(item.eqid==data?.eqid){
              item.showComments=true
            }
            else{
              item.showComments=false
            }
        })
        }  
      })
    }
   
  }
  hideComments(data){
    data.showComments=!data.showComments
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
        this.openComments(questionData)
      }
    })
  }
  deleteQuestionComment(questionData){
    this.questionService.deleteComment({"cid":questionData?.cid}).subscribe(response => {
      if (response) {
        this.openComments(questionData)
      }
    })
  }
  likeButton(data) {
    if (!data?.likedByTheRequestedUser) {
      if (data?.disLikedByTheRequestedUser) {
        data.disLikedByTheRequestedUser = false
        data.totalNumberOfDislikes--;
      }
      data.likedByTheRequestedUser = true
      data.totalNumberOfLikes++
    }
    else {
      data.likedByTheRequestedUser = false
      data.totalNumberOfLikes--
    }
    this.updateLikeButton({ "type": 1 }, data?.ctype,data.eqid)
  }
  dislikeButton(data) {
    if (!data?.disLikedByTheRequestedUser) {
      if (data?.likedByTheRequestedUser) {
        data.likedByTheRequestedUser = false
        data.totalNumberOfLikes--;
      }
      data.disLikedByTheRequestedUser = true
      data.totalNumberOfDislikes++
    }
    else {
      data.disLikedByTheRequestedUser = false
      data.totalNumberOfDislikes--
    }
    this.updateLikeButton({ "type": 0 }, data.ctype,data.eqid)
  }
  updateLikeButton(type, ctype,eqid) {
    let details = {
      "userid": this.userdetails.userid,
      "parentid": eqid,
      "updwnvt": type.type,
      "ctype": ctype
    }
    this.questionService.updateLikeButton(details).subscribe(response => {
      if (response) {
      }
    })
  }

  onValChange(value){
    this.toggleValue=value
    this.pageNumber=1
    if(this.toggleValue=='B'){
      //this.getBlog()
    }
    else{
      this.getQuestions();
    }
    
  }

  checkthis(event){
    console.log('hi')
  }
  
  onScroll(): void {
    
      this.pageNumber++;
      let details={
        "ctype":this.toggleValue,
        "userid":this.userdetails.userid,
        "pageNumber":this.pageNumber,
        "numberOfPostsRequired":this.noOfposts,
        "filterCondition":this.searchText
    }
    this.questionService.getQuestionOrEventFeed(details).subscribe(res => {
      if (res.success) {
        this.tempdata = res.data.data.map(item=>{
          return {...item, showComments:false}
      })
      this.data=[...this.data,...this.tempdata]
      }
    })
    
  }
 
  openAnswers(data) {
    if(!data.showAnswer){
      let details = {
        "eqid": data.eqid,
        "ctype": data.ctype,
        "userid": this.userdetails.userid
      }
      this.questionService.getAnswers(details).subscribe(response => {
        if (response?.data && response.data.length!=0 ) {
          this.answer = response.data[0].content
          
        } 
        else{
          this.answer='<p>No best answer yet</p>'
        }
        this.data.map(item => {
          if (item.eqid == data.eqid) {
            item.showAnswer = true
          }
          else {
            item.showAnswer = false
          }
        })
      })
    }
    
  }
  hideAnswer(data){
    data.showAnswer=!data.showAnswer
  }
  // bottomReached(): boolean {
  //   return (window.innerHeight + window.scrollY) >= document.body.offsetHeight;
  // }
}
