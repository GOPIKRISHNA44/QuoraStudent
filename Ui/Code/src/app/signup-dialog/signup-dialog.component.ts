import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { SignUpDetails } from '../models/auth.model';
import { AuthenticationService } from '../services/authentication.service';
import { formatDate, DatePipe } from '@angular/common';
import { CustomValidators } from '../providers/customvalidators';
import { Title } from '../constants/title.constants';
import { quoraConstants } from '../constants/app.constants';


@Component({
  selector: 'app-signup-dialog',
  templateUrl: './signup-dialog.component.html',
  styleUrls: ['./signup-dialog.component.css']
})
export class SignupDialogComponent implements OnInit {
  title=Title.signUpTitle
  hide: boolean = true;
  chide: boolean = true
  signUpDetails: SignUpDetails;
  signUpForm: FormGroup = this.fb.group({
    username: ['', [Validators.required, Validators.pattern("^[a-zA-Z0-9]*$")]],
    emailid: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required]],
    dob: ['', [Validators.required]],
    universitycode: ['', [Validators.required]],
    confirmPassword: ['', [Validators.required]]
  }, {
    validator: CustomValidators.mustMatch('password', 'confirmPassword')
  })
  unvList : any = [];
  numberOfAvatars :any = [...Array(quoraConstants.numberOfAvatars).keys()]
  constructor(public datepipe: DatePipe, public dialogRef: MatDialogRef<SignupDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private fb: FormBuilder, private authenticationService: AuthenticationService,) { }

  ngOnInit(): void {
    this.getUnvList();
  }
  onNoClick() {
    this.dialogRef.close();
  }
  signUp() {
    if (!this.signUpForm.valid) {
      return;
    }
    const signUpDetails: SignUpDetails = {
      username: this.signUpDetails?.username,
      emailid: this.signUpDetails?.emailid,
      password: this.signUpDetails?.password,
      dob: this.signUpDetails?.dob,
      universitycode: this.signUpDetails?.universitycode
    }
    signUpDetails.username = this.signUpForm.value.username;
    signUpDetails.emailid = this.signUpForm.value.emailid;
    signUpDetails.password = this.signUpForm.value.password;
    signUpDetails.dob = formatDate(this.signUpForm.value.dob, 'yyyy-MM-dd', 'en-US');
    signUpDetails.universitycode = this.signUpForm.value.universitycode;
    console.log("signUpForm", this.signUpForm.value)
    this.authenticationService.signUp(signUpDetails).subscribe(res => {
      if (res.data) {
        this.dialogRef.close();
      }
      else {
        alert('Reason:Login' + res?.reason)
      }
    },
      error => {
        alert('Error occured with message ' + error?.message)
      })
    this.dialogRef.close();
  }
  get f() {
    return this.signUpForm.controls;
  }

  getUnvList()
  {
    this.authenticationService.getUniversityList()
    .subscribe((data) => {
      if (data && data.success) {
        this.unvList = [];
        const unvMap = new Map(Object.entries(data["data"]["univ"]));
        for (const value of  unvMap.values()) {
          this.unvList.push(value)
        }

        console.log(this.unvList)
      }
    })
  }
 
 
}
