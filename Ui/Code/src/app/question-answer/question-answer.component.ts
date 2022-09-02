import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { AnswerDialogComponent } from '../answer-dialog/answer-dialog.component';
import { UserDetails } from '../models/auth.model';
import { AuthenticationService } from '../services/authentication.service';
import { QuestionService } from '../services/question.service';
import { sortedValues } from '../constants/title.constants';
import { Clipboard } from '@angular/cdk/clipboard';
import { SpinnerService } from '../services/spinner.service';
import { socialMediaShareURLS } from '../constants/path.contants';
import { appConstants } from '../constants/alert.constants';
import { AlertService } from '../services/alert.service';

@Component({
  selector: 'app-question-answer',
  templateUrl: './question-answer.component.html',
  styleUrls: ['./question-answer.component.css'],
  // encapsulation: ViewEncapsulation.None
  //encapsulation: ViewEncapsulation.None

})
export class QuestionAnswerComponent implements OnInit {
  questionData: any
  answerData: any
  commentsData: any
  answerCommentsData: any
  userdetails: UserDetails;
  showComments = false;
  comment: string = '';
  answerComment = ""
  isliked: boolean;
  disliked: boolean;
  eqid: string;
  ctype: string
  selectedSortValue = null
  likeCount: number;
  dislikeCount: number;

  sortedValues = sortedValues;

  ShareURLS = socialMediaShareURLS
  tagsId: any;

  constructor(private clipboard: Clipboard, public dialog: MatDialog, private alertServc: AlertService,
    private router: Router, private questionService: QuestionService, private authenticationService: AuthenticationService,
    private route: ActivatedRoute, private spinnerService: SpinnerService) { }

  ngOnInit(): void {
    this.userdetails = JSON.parse(this.authenticationService.GetUserDetails())
    this.eqid = this.route.snapshot.queryParams['eqid']
    this.ctype = this.route.snapshot.queryParams['ctype']

    let details = {
      "eqid": this.eqid,
      "ctype": this.ctype,
      "userid": this.userdetails.userid
    }
    this.questionService.getQuestionDetails(details).subscribe(res => {
      if (res.success) {
        this.tagsId = res.data?.tags?.split(';').filter((a) => a)
        this.questionData = { ...res.data, tagsId: this.tagsId }
        this.disliked = this.questionData.disLikedByTheRequestedUser
        this.isliked = this.questionData.likedByTheRequestedUser
        this.likeCount = this.questionData.totalNumberOfLikes
        this.dislikeCount = this.questionData.totalNumberOfDislikes
        //this.questionService.updateQuestion(res?.data)
        console.log(res)
        this.getAnswers()
      }
    })

  }
  getAnswers() {
    let details = {
      "eqid": this.eqid,
      "ctype": this.ctype,
      "userid": this.userdetails.userid
    }
    this.questionService.getAnswers(details).subscribe(response => {
      if (response) {
        this.answerData = response.data.map(item => {
          return { ...item, showAnswerComments: false }
        })
      }

    })
  }
  openQuestion() {
    const dialogRef = this.dialog.open(AnswerDialogComponent, {
      width: '850px',
      data: {
        question: this.questionData?.question,
        details: {
          userid: this.userdetails.userid,
          eqid: this.eqid,
          ctype: this.ctype,
          username: this.userdetails.username,
          avatarid: this.userdetails.avatarid
        },


      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        console.log(result)
        this.getAnswers()

      }
    });
  }

  sendComment() {
    let sendCommentDetails = {
      "userid": this.userdetails.userid,
      "parentid": this.questionData.eqid,
      "ctype": this.questionData.ctype,
      "comment": this.comment
    }
    this.spinnerService.disableLoader();
    this.questionService.sendComments(sendCommentDetails).subscribe(response => {
      if (response) {
        this.comment = ''
        this.openComments()
      }
    })
    this.spinnerService.enableLoader();
  }
  likeButton(isliked, ctype) {
    if (!isliked) {
      if (this.disliked) {
        this.disliked = false
        this.dislikeCount--;
      }
      this.isliked = true
      this.likeCount++
    }
    else {
      this.isliked = false
      this.likeCount--
    }
    this.spinnerService.disableLoader();
    this.updateLikeButton({ "type": 1 }, ctype);
    this.spinnerService.enableLoader();
  }
  dislikeButton(disliked, ctype) {
    if (!disliked) {
      if (this.isliked) {
        this.isliked = false
        this.likeCount--;
      }
      this.disliked = true
      this.dislikeCount++
    }
    else {
      this.disliked = false
      this.dislikeCount--
    }
    this.spinnerService.disableLoader();
    this.updateLikeButton({ "type": 0 }, ctype)
    this.spinnerService.enableLoader();
  }
  updateLikeButton(type, ctype) {
    let details = {
      "userid": this.userdetails.userid,
      "parentid": this.eqid,
      "updwnvt": type.type,
      "ctype": ctype
    }
    this.questionService.updateLikeButton(details).subscribe(response => {
      if (response) {
      }
    })
  }
  answerLikeButton(likeQuestionData) {
    if (!likeQuestionData.likedByTheRequestedUser) {
      if (likeQuestionData.disLikedByTheRequestedUser) {
        likeQuestionData.disLikedByTheRequestedUser = false
        likeQuestionData.totalNumberOfDislikes--;
      }
      likeQuestionData.likedByTheRequestedUser = true
      likeQuestionData.totalNumberOfLikes++
    }
    else {
      likeQuestionData.likedByTheRequestedUser = false
      likeQuestionData.totalNumberOfLikes--
    }
    this.spinnerService.disableLoader();
    this.updateAnswerLikeButton({ "type": 1, "aid": likeQuestionData.aid })
    this.spinnerService.enableLoader();
  }
  answerDislikeButton(dislikeQuestionData) {
    if (!dislikeQuestionData.disLikedByTheRequestedUser) {
      if (dislikeQuestionData.likedByTheRequestedUser) {
        dislikeQuestionData.likedByTheRequestedUser = false
        dislikeQuestionData.totalNumberOfLikes--;
      }
      dislikeQuestionData.disLikedByTheRequestedUser = true
      dislikeQuestionData.totalNumberOfDislikes++
    }
    else {
      dislikeQuestionData.likedByTheRequestedUser = false
      dislikeQuestionData.totalNumberOfLikes--
    }
    this.spinnerService.disableLoader();
    this.updateAnswerLikeButton({ "type": 0, "aid": dislikeQuestionData.aid })
    this.spinnerService.enableLoader();
  }
  updateAnswerLikeButton(data) {
    let details = {
      "userid": this.userdetails.userid,
      "parentid": data.aid,
      "updwnvt": data.type,
      "ctype": "A"
    }
    this.questionService.updateLikeButton(details).subscribe(response => {
      if (response) {
      }
    })
  }
  openComments() {
    let commentDetails = {
      "requestingUserId": this.userdetails.userid,
      "ctype": this.questionData.ctype,
      "eqabcid": this.questionData.eqid
    }
    this.questionService.getComments(commentDetails).subscribe(response => {
      if (response) {
        this.commentsData = response
        this.showComments = true
      }

    })
  }
  openAnswerComments(aid) {
    let commentDetails = {
      "requestingUserId": this.userdetails.userid,
      "ctype": "A",
      "eqabcid": aid
    }
    this.questionService.getComments(commentDetails).subscribe(response => {
      if (response) {
        this.answerCommentsData = response
        this.answerData.map(item => {
          if (item.aid == aid) {
            item.showAnswerComments = true
          }
          else {
            item.showAnswerComments = false
          }
        })

      }

    })
  }
  sendAnswerComment(aid) {
    let sendCommentDetails = {
      "userid": this.userdetails.userid,
      "parentid": aid,
      "ctype": "A",
      "comment": this.answerComment
    }
    this.questionService.sendComments(sendCommentDetails).subscribe(response => {
      if (response) {
        this.answerComment = ''
        this.openAnswerComments(aid)
      }
    })
  }
  deleteComment(cid, aid) {
    this.spinnerService.disableLoader();
    this.questionService.deleteComment({ "cid": cid }).subscribe(response => {
      if (response) {
        this.openAnswerComments(aid)
      }
    })
    this.spinnerService.enableLoader();
  }
  deleteQuestionOrEvent(eqid, ctype) {
    this.spinnerService.disableLoader();
    if (ctype == "Q") {
      this.questionService.deleteQuestion({ "eqid": eqid, "ctype": ctype }).subscribe(response => {
        if (response) {
          this.alertServc.successAlert(appConstants.delete);
          this.router.navigate(['/home'])
        }
      })
    }
    else {
      let payLoad = {
        "askAquestionDTO": { "eqid": eqid },
      }
      this.questionService.deleteEvent( { "eqid": eqid, "ctype": ctype } ).subscribe(response => {
        if (response) {
          this.alertServc.successAlert(appConstants.delete);
          this.router.navigate(['/home'])
        }
      })
    }


    this.spinnerService.enableLoader();
  }
  deleteAnswer(aid) {
    this.spinnerService.disableLoader();
    this.questionService.deleteAnswer({ "aid": aid }).subscribe(response => {
      if (response) {
        this.getAnswers()
      }
    })
    this.spinnerService.enableLoader();
  }
  deleteQuestionComment(cid) {
    this.spinnerService.disableLoader();
    this.questionService.deleteComment({ "cid": cid }).subscribe(response => {
      if (response) {
        this.openComments()
      }
    })
    this.spinnerService.enableLoader();
  }
  onChange(event) {
    if (event.value == "1") {
      this.answerData.sort((a, b) => b.totalNumberOfLikes - a.totalNumberOfLikes)
    }
    else {
      this.answerData.sort((a, b) => a.doa - b.doa)
    }
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


}
