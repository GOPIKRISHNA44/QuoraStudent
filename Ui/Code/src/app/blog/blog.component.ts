import { Component, Inject, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { QuillConfiguration, Title } from '../constants/title.constants';
import { UserDetails } from '../models/auth.model';
import { AuthenticationService } from '../services/authentication.service';
import { QuestionService } from '../services/question.service';

@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.css']
})
export class BlogComponent implements OnInit {
  quillConfiguration = QuillConfiguration
  placeholder = Title.blogPlaceholder
  editorText: string;
  userdetails:UserDetails ;
  interests = []
  tags = new FormControl('');
  textTitle:string;
  constructor( private questionService: QuestionService, private authenticationService: AuthenticationService,) { }

  ngOnInit(): void {
    this.userdetails=JSON.parse(this.authenticationService.GetUserDetails())
    this.questionService.getInterests().subscribe(res => {
      if (res.success) {
        this.interests = res.data.interests
      }
    })
  }
  submit() {
    let sentText={
      "userid":this.userdetails?.userid,
      "content":this.editorText,
      "title":this.textTitle
    } 
    this.questionService.postBlog(sentText).subscribe(res => {
      if (res.success) {
        console.log(res)
      }
    })

  }
  
}