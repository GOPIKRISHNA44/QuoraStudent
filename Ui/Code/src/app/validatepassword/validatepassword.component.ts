import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';
import { OldPwdValidators } from './OldPwdValidators';


@Component({
  selector: 'app-validatepassword',
  templateUrl: './validatepassword.component.html',
  styleUrls: ['./validatepassword.component.css']
})
export class ValidatepasswordComponent implements OnInit {
  e: string;
  q: string;
  status = 0;
  pwChangeForm: FormGroup;
  form1: FormGroup; 

  // Properties that store paths to FormControls makes our template less verbose
  current: AbstractControl;
  newPW: AbstractControl;
  confirm: AbstractControl;
  constructor(private route: ActivatedRoute, private authSer: AuthenticationService,private fb: FormBuilder,private router: Router) {

    this.e = this.route.snapshot.queryParamMap.get('e');
    this.q = this.route.snapshot.queryParamMap.get('q');
   
  
    this.authSer.validateResetPassword(this.e, this.q).subscribe((data) => {
      if (data && data.success) {
        this.status = 1;
      }
      else {
        this.status = 2;
      }
    })
    this.form1 = fb.group({
      'oldPwd': ['',Validators.required,OldPwdValidators.shouldBe1234],
      'newPwd': ['',Validators.required],
      'confirmPwd': ['',Validators.required]
    }, {
      validator: OldPwdValidators.matchPwds
    });

  }
  ngOnInit(): void {
  }

 

get oldPwd(){
  return this.form1.get('oldPwd');
}

 get newPwd(){
  return this.form1.get('newPwd');
}

 get confirmPwd(){
  return this.form1.get('confirmPwd');
}

updatePassword() {
  const np = this.form1.get('newPwd').value;
  this.authSer.updatePassword(this.e, np  ,this.q)
  .subscribe((data)=>{
    if(data&&data.success)
    {
      this.router.navigate(['/login']);
    }
  })
}

}
