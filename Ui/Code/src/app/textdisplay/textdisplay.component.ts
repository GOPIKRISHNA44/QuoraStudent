import { Component, Input, OnInit, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-textdisplay',
  templateUrl: './textdisplay.component.html',
  styleUrls: ['./textdisplay.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class TextdisplayComponent implements OnInit {

  
  @Input() text = '';
  constructor() { }

  ngOnInit(): void {
  }

}
