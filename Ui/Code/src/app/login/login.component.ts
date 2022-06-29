import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { SignupDialogComponent } from '../signup-dialog/signup-dialog.component';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../services/authentication.service';
import { LoginDetails } from '../models/auth.model';
// import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  hide: boolean = true;
  loginDetails:LoginDetails
  loginForm: FormGroup = this.fb.group({
    username: ['', [Validators.required]],
    password: ['', [Validators.required]]
  })
  constructor(
    public dialog: MatDialog,
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private authenticationService: AuthenticationService,
  ) { }

  ngOnInit() {
  }

  login() {
    if (!this.loginForm.valid) {
      return;
    }
    const loginDetails: LoginDetails = {
      username: this.loginDetails?.username,
      email: this.loginDetails?.email,
      password:this.loginDetails?.password
    }
    if(this.loginForm.value.username.includes('@')){
      loginDetails.username=null;
      loginDetails.email=this.loginForm.value.username
    }
    else{
      loginDetails.username=this.loginForm.value.username;
      loginDetails.email=null;
    }
    loginDetails.password=this.loginForm.value.password
    this.authenticationService.login(loginDetails).subscribe(res => {
      if(res.success){
        localStorage.setItem('token',res?.data.sessionkey)
        this.router.navigate(['/'])
      }
      else{
        alert('Reason:'+ res?.reason)
      }
    },
      error => {
        alert('Error occured with message ' + error?.message)
      })
  }    
  signUp() {
    const dialogRef = this.dialog.open(SignupDialogComponent, {
      width: '600px',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        console.log(result)
      }
    });

  }
}
