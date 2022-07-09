import { NonNullAssert } from '@angular/compiler';
import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Title } from '../constants/title.constants';
import { UserDetails } from '../models/auth.model';
import { AuthenticationService } from '../services/authentication.service';
import { QuestionService } from '../services/question.service';

@Component({
  selector: 'app-interests-dialog',
  templateUrl: './interests-dialog.component.html',
  styleUrls: ['./interests-dialog.component.css']
})
export class InterestsDialogComponent implements OnInit {
  interests = []
  selectedOptions = []
  userdetails: UserDetails;
  constructor(public dialogRef: MatDialogRef<InterestsDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private questionService: QuestionService,
    private authenticationService: AuthenticationService) { }

  title = Title.interestsTitle
  ngOnInit(): void {
    this.questionService.getInterests().subscribe(res => {
      if (res.success) {
        this.interests = res.data.interests
      }
    })
    this.userdetails = JSON.parse(this.authenticationService.GetUserDetails())
  }
  onNoClick() {
    let objInterest = {
      userid:this.userdetails?.userid,
      interests:null,
    }
    this.questionService.postInterests(objInterest).subscribe(res => {
      if (res.success) {
        
      }
    })

    this.dialogRef.close();
  }
  submit() {
    let objInterest = {
      userid:this.userdetails?.userid,
      interests:this.selectedOptions,
    }
    this.questionService.postInterests(objInterest).subscribe(res => {
      if (res.success) {
        
      }
    })
    this.dialogRef.close();
  }

}
