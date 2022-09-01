import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { UserDetails } from '../models/auth.model';
import { QuillComponent } from '../quill/quill.component';
import { AuthenticationService } from '../services/authentication.service';
import { QuestionService } from '../services/question.service';
import { SpinnerService } from '../services/spinner.service';

@Component({
  selector: 'app-myquestionsorevents',
  templateUrl: './myquestionsorevents.component.html',
  styleUrls: ['./myquestionsorevents.component.css']
})
export class MyquestionsoreventsComponent implements OnInit {
  ctype: string
  userdetails: UserDetails;
  myQuestionOrEventTitle=''
  myData: any
  tagsId: any;
  constructor(public dialog: MatDialog,private router: Router,private spinnerService:SpinnerService,private route: ActivatedRoute, private questionService: QuestionService, private authenticationService: AuthenticationService,) { }

  ngOnInit(): void {
    // this.ctype = this.route.snapshot.queryParams['ctype']
    this.userdetails = JSON.parse(this.authenticationService.GetUserDetails())
    this.questionService.requiredCtype$.subscribe((value) => {
      if(value && value!={}){
        this.ctype = value;
      }
      else{
        this.ctype = 'Q'
      }
      this.myQuestions()
      this.myQuestionOrEventTitle=this.ctype == 'Q'?'MY QUESTIONS':'MY EVENTS'
    });
    
    
  }
  myQuestions() {
    let details = {
      "userid": this.userdetails.userid,
      "ctype": this.ctype?this.ctype:'Q'
    }
    this.questionService.getMyQuestions(details).subscribe(res => {
      if (res.success) {
        this.myData = res.data.map(item=>{
          this.tagsId = item?.tags?.split(';').filter((a) => a)
          return {...item,tagsId:this.tagsId}
      })
      }
    })
  }

  deleteQuestion(eqid,ctype){
    this.spinnerService.disableLoader();

    if(ctype=="E")
    {
      this.questionService.deleteEvent({ "eqid": eqid, "ctype": ctype }).subscribe(response => {
        if (response) {
          this.myQuestions()
        }
      })
    }
    else
    {
      this.questionService.deleteQuestion({ "eqid": eqid, "ctype": ctype }).subscribe(response => {
        if (response) {
          this.myQuestions()
        }
      })
    }

    


    this.spinnerService.enableLoader();
  }
 
  likeButton(likeQuestionData) {
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
    this.updateQuestionLikeButton(likeQuestionData)
    this.spinnerService.enableLoader();
  }
  dislikeButton(dislikeQuestionData) {
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
    this.updateQuestionLikeButton(dislikeQuestionData)
    this.spinnerService.enableLoader();
  }
  updateQuestionLikeButton(data) {
    let details = {
      "userid": this.userdetails.userid,
      "parentid": data.eqid,
      "updwnvt": data.type,
      "ctype": this.ctype
    }
    this.questionService.updateLikeButton(details).subscribe(response => {
      if (response) {
      }
    })
  }
  copyUrl(){

  }
  openQuestion(data){
    this.router.navigate(['home/question/'],{queryParams:{'eqid':data?.eqid,'ctype':data?.ctype}})
      
  }
  editQuestion(data) {
    // const dialogRef = this.dialog.open(AnswerDialogComponent, {
    //   width: '850px',
    //   data: {
    //     question: this.questionData?.question,
    //     details: {
    //       userid: this.userdetails.userid,
    //       eqid: this.eqid,
    //       ctype: this.ctype,
    //       username: this.userdetails.username,
    //       avatarid: this.userdetails.avatarid
    //     },


    //   }
    // });

    const dialogRef = this.dialog.open(QuillComponent, {
      width: '60%',
      data:{
        data:data,
        isQuestion:this.ctype=='Q'?true:false,
        isEvent:this.ctype=='Q'?false:true,
      } 
      
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.ctype=result.ctype
       this.myQuestions()
      }
    });
  }
}
