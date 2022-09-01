import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { socialMediaShareURLS } from '../constants/path.contants';
import { HomeComponent } from '../home/home.component';
import { UserDetails } from '../models/auth.model';
import { AuthenticationService } from '../services/authentication.service';
import { QuestionService } from '../services/question.service';
import { SpinnerService } from '../services/spinner.service';
import { Clipboard } from '@angular/cdk/clipboard';
@Component({
  selector: 'app-individual-blog',
  templateUrl: './individual-blog.component.html',
  styleUrls: ['./individual-blog.component.css']
})
export class IndividualBlogComponent implements OnInit {
  data: any;
  noOfposts = 6
  userdetails: UserDetails;
  comment = ''
  pageNumber = 1;
  tempdata: any;
  @Input() searchText = ''
  commentsData: any;
  ShareURLS = socialMediaShareURLS
  bid: string
  ctype: string
  tagsId: any;
  constructor(private route: ActivatedRoute, private clipboard: Clipboard, private router: Router, private spinnerService: SpinnerService, private homeComponent: HomeComponent, private authenticationService: AuthenticationService, private questionService: QuestionService) { }

  ngOnInit(): void {
    this.questionService.blogDetails$.subscribe((value) => {
      this.data = value
    })
    this.userdetails = JSON.parse(this.authenticationService.GetUserDetails())
    this.bid = this.route.snapshot.queryParams['bid']
    this.ctype = this.route.snapshot.queryParams['ctype']
    let details = {
      "userid": this.userdetails.userid
    }
    if (this.data.length == 0) {
      this.questionService.getMyBlogs(details).subscribe(res => {
        if (res.success) {
          this.data = res.data.filter(item => {
            return item.bid == this.bid
          })[0]
        }
        this.tagsId = this.data?.tags?.split(';').filter((a) => a)
        this.data={...this.data,tagsId:this.tagsId}
      })
    }

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

    this.updateLikeButton({ "type": 1 }, "B", data.bid)
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
        "eqabcid": data?.bid
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
  openBlog(data) {
    this.questionService.setBlogDetails(data)
    this.homeComponent.openBlog(data)
  }
  editBlog(data) {
    this.questionService.setEditBlogDetails(data)
    this.router.navigate(['home/editBlogs'])
  }
  copyUrl() {

    this.clipboard.copy(window.location.href);

  }
  shareFacebook() {
    const facebookUrl = this.ShareURLS.facebook
    const navUrl = facebookUrl + window.location.href
    window.open(navUrl, '_blank');
  }

  shareTwitter() {
    const twitterUrl = this.ShareURLS.twitter
    const navUrl = twitterUrl + window.location.href
    window.open(navUrl, '_blank');
  }
  deleteBlog(bid) {
    this.spinnerService.disableLoader();
    this.questionService.deleteBlog({ "bid": bid }).subscribe(response => {
      if (response) {
        this.questionService.setCtype("B");
        this.homeComponent.myBlogs()
      }
    })
    this.spinnerService.enableLoader();
  }

}
