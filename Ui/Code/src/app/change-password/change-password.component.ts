import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Title } from '../constants/title.constants';
import { ChangeDetails, LoginDetails } from '../models/auth.model';
import { AuthenticationService } from '../services/authentication.service';
import { CustomValidators } from '../providers/customvalidators';
import { AlertService } from '../services/alert.service';
import { appConstants } from '../constants/alert.constants';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
  changeTitle = Title.changePassword
  hideo: boolean = true;
  hiden: boolean = true;
  hidec: boolean = true;

  changeDetails: ChangeDetails
  changeForm: FormGroup = this.fb.group({
    newpassword: ['', [Validators.required]],
    oldpassword: ['', [Validators.required]],
    cpassword: ['', [Validators.required]]
  }, {
    validator: CustomValidators.mustMatch('oldpassword', 'cpassword')
  })
  constructor(private router: Router,
    private fb: FormBuilder,private alertServc: AlertService,
    private authenticationService: AuthenticationService,) { }

  ngOnInit(): void {
  }
  handleKeyUp(e) {
    if (e.keyCode === 13) {
      this.confirm();
    }
  }
  confirm() {
this.authenticationService.logout().subscribe(res => {
  if (res.success) {
    localStorage.removeItem('token');
    localStorage.removeItem('userdetails');
    this.router.navigate(['/login'])
  } else {
    alert('Reason:home' + res?.reason)
  }
  this.alertServc.successAlert(appConstants.loggedOut);
},
  error => {
    alert('Error occured with message ' + error?.message)
  })
}

  cancel(){
    this.router.navigate(['/home'])
  }
  get f() {
    return this.changeForm.controls;
  }
}
