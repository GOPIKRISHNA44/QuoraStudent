import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {  MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
@Component({
  selector: 'app-signup-dialog',
  templateUrl: './signup-dialog.component.html',
  styleUrls: ['./signup-dialog.component.css']
})
export class SignupDialogComponent implements OnInit {
  validPattern = "^[a-zA-Z0-9]$"
  signUpForm: FormGroup = this.fb.group({
    username: ['', [Validators.required,Validators.pattern('^[a-zA-Z]+$')]],
    email:['',[Validators.required,Validators.email]],
    password: ['', [Validators.required]],
    dob:[''],
    university:[''],
    cpassword:['', [Validators.required]]
  })
  constructor( public dialogRef: MatDialogRef<SignupDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,private fb: FormBuilder,) { }

  ngOnInit(): void {
  }
  onNoClick(){
    this.dialogRef.close();
  }
  signUp(){
    
    this.dialogRef.close();
  }
}
