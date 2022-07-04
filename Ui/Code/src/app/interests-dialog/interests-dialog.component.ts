import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Title } from '../constants/title.constants';

@Component({
  selector: 'app-interests-dialog',
  templateUrl: './interests-dialog.component.html',
  styleUrls: ['./interests-dialog.component.css']
})
export class InterestsDialogComponent implements OnInit {

  constructor( public dialogRef: MatDialogRef<InterestsDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }
  title=Title.interestsTitle
  ngOnInit(): void {
  }
  onNoClick() {
    this.dialogRef.close();
  }
}
