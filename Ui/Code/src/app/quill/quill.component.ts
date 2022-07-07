import { Component, Input, OnInit,Output, EventEmitter  } from '@angular/core';
import { EditorChangeContent, EditorChangeSelection } from 'ngx-quill';
import { QuillConfiguration } from '../constants/title.constants';
@Component({
  selector: 'app-quill',
  templateUrl: './quill.component.html',
  styleUrls: ['./quill.component.css']
})
export class QuillComponent implements OnInit {
  @Input() placeholder:string;
  @Output() text = new EventEmitter<string>();
  quillConfiguration =QuillConfiguration 
  
  constructor() { }

  ngOnInit(): void {
  }
  blured = false
  focused = false

  created(event) {
    // tslint:disable-next-line:no-console
    console.log('editor-created', event)
  }

  changedEditor(event: EditorChangeContent | EditorChangeSelection) {
    // tslint:disable-next-line:no-console
    console.log('editor-change', event)
  }

  focus($event) {
    // tslint:disable-next-line:no-console
    console.log('focus', $event)
    this.focused = true
    this.blured = false
  }

  blur($event) {
    // tslint:disable-next-line:no-console
    console.log('blur', $event)
    this.focused = false
    this.blured = true
  }
}
