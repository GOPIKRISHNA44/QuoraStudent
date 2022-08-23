import { Component, Input, OnInit } from '@angular/core';
import { UserDetails } from '../models/auth.model';
import { AuthenticationService } from '../services/authentication.service';
import { QuestionService } from '../services/question.service';

@Component({
  selector: 'app-show-blog',
  templateUrl: './show-blog.component.html',
  styleUrls: ['./show-blog.component.css']
})
export class ShowBlogComponent implements OnInit {
  noOfposts = 6
  userdetails: UserDetails;
  data: any;
  comment = ''
  pageNumber = 1;
  tempdata: any;
  @Input() searchText = ''
  commentsData: any;
  scroll = true;
  constructor(private authenticationService: AuthenticationService, private questionService: QuestionService) { }

  ngOnInit(): void {
    this.userdetails = JSON.parse(this.authenticationService.GetUserDetails())
    this.getBlog()
  }

  getBlog() {
    let details = {
      "ctype": 'B',
      "userid": this.userdetails.userid,
      "pageNumber": 1,
      "numberOfPostsRequired": this.noOfposts,
      "filterCondition": ""
    }
    this.questionService.getBlogFeed(details).subscribe(res => {
      if (res.success) {
        this.data = res.data.data.map(item => {
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
  onScroll() {
    this.pageNumber++;
    let details={
      "ctype":"B",
      "userid":this.userdetails.userid,
      "pageNumber":this.pageNumber,
      "numberOfPostsRequired":this.noOfposts,
     "filterCondition":this.searchText
  }
    this.questionService.getQuestionOrEventFeed(details).subscribe(res => {
      if (res.success && res.data?.data.length != 0) {
        this.tempdata = res.data.data.map(item => {
          return { ...item, showComments: false }
        })
        this.data = [...this.data, ...this.tempdata]
      }
      else {
        this.scroll = false
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
    this.updateLikeButton({ "type": 0 }, data.ctype, data.bid)
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
}
