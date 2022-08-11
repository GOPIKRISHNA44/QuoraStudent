import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { AnswerDialogComponent } from '../answer-dialog/answer-dialog.component';
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
  answerData: any
  commentsData: any
  userdetails: UserDetails;
  showComments = false;
  comment: string = '';
  isliked: boolean;
  disliked: boolean;
  eqid: string;
  ctype: string
  date: string;
  likeCount: number;
  dislikeCount: number;
  constructor(public dialog: MatDialog, private questionService: QuestionService, private authenticationService: AuthenticationService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.userdetails = JSON.parse(this.authenticationService.GetUserDetails())
    this.eqid = this.route.snapshot.queryParams['eqid']
    this.ctype = this.route.snapshot.queryParams['ctype']
    this.isliked

    let details = {
      "eqid": this.eqid,
      "ctype": this.ctype,
      "userid": this.userdetails.userid
    }
    this.questionService.getQuestionDetails(details).subscribe(res => {
      if (res.success) {
        this.questionData = res.data
        this.date = new Date(this.questionData.doq).toLocaleDateString()
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
        this.answerData = response
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
  sendComment() {
    let sendCommentDetails = {
      "userid": this.userdetails.userid,
      "parentid": this.questionData.eqid,
      "ctype": this.questionData.ctype,
      "comment": this.comment
    }
    this.questionService.sendComments(sendCommentDetails).subscribe(response => {
      if (response) {
        this.comment = ''
        this.openComments()
      }
    })
  }
}
