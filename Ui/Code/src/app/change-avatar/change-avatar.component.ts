import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { quoraConstants } from '../constants/app.constants';
import { UserDetails } from '../models/auth.model';
import { AuthenticationService } from '../services/authentication.service';
import { QuestionService } from '../services/question.service';

@Component({
  selector: 'app-change-avatar',
  templateUrl: './change-avatar.component.html',
  styleUrls: ['./change-avatar.component.css']
})
export class ChangeAvatarComponent implements OnInit {
  userdetails: UserDetails;
  selectedValue: string
  constructor(public dialogRef: MatDialogRef<ChangeAvatarComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private fb: FormBuilder,
    private questionService: QuestionService, private authenticationService: AuthenticationService,) { }
  numberOfAvatars: any 
  ngOnInit(): void {
    this.userdetails = JSON.parse(this.authenticationService.GetUserDetails())
    this.numberOfAvatars = Array.from({ length: quoraConstants.numberOfAvatars }, (_, index) => index + 1);
  }
  changeAvatar(){
    if( this.selectedValue){
      let details = {
        "userid": this.userdetails.userid,
        "avatarid": this.selectedValue
      }
      this.questionService.changeAvatar(details).subscribe(res => {
        if (res.success) {
          
          window.location.reload()
        }
      })
      this.dialogRef.close();
    }
   
  }
  onNoClick() {
    this.dialogRef.close();
  }
  selectedAvatars(i){
    this.selectedValue=i
  }
}
