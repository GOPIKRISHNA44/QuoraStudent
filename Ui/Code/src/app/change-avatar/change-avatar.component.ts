import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { quoraConstants } from '../constants/app.constants';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-change-avatar',
  templateUrl: './change-avatar.component.html',
  styleUrls: ['./change-avatar.component.css']
})
export class ChangeAvatarComponent implements OnInit {

  constructor( public dialogRef: MatDialogRef<ChangeAvatarComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private fb: FormBuilder, private authenticationService: AuthenticationService,) { }
  numberOfAvatars :any = Array.from({length: quoraConstants.numberOfAvatars}, (_, index) => index + 1);
  ngOnInit(): void {
  }
  changeAvatar(){
    this.dialogRef.close();
  }
  onNoClick(){
    this.dialogRef.close();
  }
}
