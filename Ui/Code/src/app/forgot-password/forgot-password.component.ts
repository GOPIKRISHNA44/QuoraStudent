import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Title } from '../constants/title.constants';
import { AlertService } from '../services/alert.service';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {
  title=Title.forgotPassword
  constructor(private router: Router,
    private fb: FormBuilder,private alertServc: AlertService,
    private authenticationService: AuthenticationService,) { }
  forgotForm: FormGroup = this.fb.group({
    emailid: ['', [Validators.required, Validators.email]],
  })
  ngOnInit(): void {
  }
  onNoClick(){
    this.router.navigate(['/login'])
  }
  send(){
    this.router.navigate(['/login'])
  }
  
  handleKeyUp(e) {
    if (e.keyCode === 13) {
      this.send();
    }
  }
  get f() {
    return this.forgotForm.controls;
  }
}
