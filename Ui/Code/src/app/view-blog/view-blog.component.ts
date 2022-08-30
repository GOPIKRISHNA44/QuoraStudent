import { Component, OnInit } from '@angular/core';
import { UserDetails } from '../models/auth.model';
import { AuthenticationService } from '../services/authentication.service';
import { QuestionService } from '../services/question.service';
import { SpinnerService } from '../services/spinner.service';

@Component({
  selector: 'app-view-blog',
  templateUrl: './view-blog.component.html',
  styleUrls: ['./view-blog.component.css']
})
export class ViewBlogComponent implements OnInit {
  noOfposts = 6
  userdetails: UserDetails;
  data: any;
  comment = ''
  pageNumber = 1;
  tempdata: any;
  commentsData: any;
  scroll = true;
  constructor(private spinnerService:SpinnerService,private authenticationService: AuthenticationService, private questionService: QuestionService) { }

  ngOnInit(): void {
    this.userdetails = JSON.parse(this.authenticationService.GetUserDetails())
    this.getMyBlog()
  }
  getMyBlog(){
    let details={
      "userid":this.userdetails.userid
    }
    this.questionService.getMyBlogs(details).subscribe(res => {
      if (res.success) {
        this.data = res.data.map(item => {
          return { ...item, showComments: false, showAnswer: false }
        })
      }
    })
  }
  sendComment(data) {
    let sendCommentDetails = {
      "userid": this.userdetails.userid,
      "parentid": data.bid,
      "ctype": "B",
      "comment": this.comment
    }
    this.questionService.sendComments(sendCommentDetails).subscribe(response => {
      if (response) {
        this.comment = ''
        this.openComments(data)
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

    this.updateLikeButton({ "type": 1 }, "B",data.bid)
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
    this.updateLikeButton({ "type": 0 },"B", data.bid)
  }
  updateLikeButton(type, ctype, bid) {
    let details = {
      "userid": this.userdetails.userid,
      "parentid": bid,
      "updwnvt": type.type,
      "ctype": ctype
    }
    this.questionService.updateLikeButton(details).subscribe(response => {
      if (response) {
      }
    })
  }

  openComments(data) {
    if (!data.showComments) {
      let commentDetails = {
        "requestingUserId": this.userdetails.userid,
        "ctype": 'B',
        "eqabcid":data?.bid
      }
      this.questionService.getComments(commentDetails).subscribe(response => {
        if (response) {
          this.commentsData = response
          this.data.map(item => {
            if (item.bid == data?.bid) {
              item.showComments = true
            }
            else {
              item.showComments = false
            }
          })
        }
      })
    }

  }
  hideComments(data) {
    data.showComments = !data.showComments
  }
  deleteQuestionComment(data) {
    this.questionService.deleteComment({ "cid": data?.cid }).subscribe(response => {
      if (response) {
        this.openComments(data)
      }
    })
  }
  editBlog(data){

  }
  copyUrl(){

  }
  deleteBlog(bid){
    this.spinnerService.disableLoader();
    this.questionService.deleteQuestion({ "bid": bid}).subscribe(response => {
      if (response) {
        this.getMyBlog()
      }
    })
    this.spinnerService.enableLoader();
  }
}
