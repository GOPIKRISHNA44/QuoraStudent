import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { SignupDialogComponent } from '../signup-dialog/signup-dialog.component';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  hide: boolean = true;
  loginForm:FormGroup = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(6)]]
  })
  constructor(
    public dialog: MatDialog,
      private route: ActivatedRoute,
      private router: Router,
      private fb: FormBuilder,
      // private authenticationService: AuthenticationService
      ) { }

      ngOnInit() {
        
        // reset login status
       // this.authenticationService.logout();

        // get return url from route parameters or default to '/'
       
    }
   
  login() {
      if (!this.loginForm.valid) {
        return;
      }
      console.log(this.loginForm.value);
      this.router.navigate(['/home'])
    }
      
  //     this.authenticationService.login(this.model.username, this.model.password)
  //         .subscribe(
  //             data => {
  //                 this.router.navigate([this.returnUrl]);
  //             },
  //             error => {
  //                 this.loading = false;
  //             });
  

  signUp(){
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
