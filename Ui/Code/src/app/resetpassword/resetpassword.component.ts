import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-resetpassword',
  templateUrl: './resetpassword.component.html',
  styleUrls: ['./resetpassword.component.css']
})
export class ResetpasswordComponent implements OnInit {

 
  isEmailValid: boolean = false;
  constructor(
    public dialogRef: MatDialogRef<ResetpasswordComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,private authSer:AuthenticationService
  ) { }

  emailFormControl = new FormControl('', [Validators.required, Validators.email]);

  matcher = new ErrorStateMatcher();

  onNoClick(): void {
    this.dialogRef.close({data:"hi"});
  }

  ngOnInit(): void {
  }

  sendReport() {
    if (!this.emailFormControl.hasError('email')) {
      console.log(this.emailFormControl.value)
      let emailid=this.emailFormControl.value;
      this.authSer.resetPassword(emailid).subscribe((res)=>{
        console.log(res)
        this.onNoClick();
      })

    }
  }
}