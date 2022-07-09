import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { SignupDialogComponent } from '../signup-dialog/signup-dialog.component';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../services/authentication.service';
import { LoginDetails } from '../models/auth.model';
import { Title } from '../constants/title.constants';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  mainTitle=Title.mainTitle
  loginTitle=Title.loginTitle
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
      emailid: this.loginDetails?.emailid,
      password:this.loginDetails?.password
    }
    if(this.loginForm.value.username.includes('@')){
      loginDetails.username=null;
      loginDetails.emailid=this.loginForm.value.username
    }
    else{
      loginDetails.username=this.loginForm.value.username;
      loginDetails.emailid=null;
    }
    loginDetails.password=this.loginForm.value.password
    this.authenticationService.login(loginDetails).subscribe(res => {
      if(res.data){
        localStorage.setItem('token',res?.data?.sessionkey)
        localStorage.setItem('userdetails',JSON.stringify(res?.data?.userdetails))
        this.router.navigate(['/'])
      }
      else{
        alert('Reason:Login'+ res?.reason)
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
  handleKeyUp(e){
    if(e.keyCode === 13){
       this.login();
    }
 }
}
