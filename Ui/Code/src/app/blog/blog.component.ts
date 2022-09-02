import { Component, Inject, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { appConstants } from '../constants/alert.constants';
import { QuillConfiguration, Title } from '../constants/title.constants';
import { UserDetails } from '../models/auth.model';
import { AlertService } from '../services/alert.service';
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
  userdetails: UserDetails;
  interests = []
  tags = new FormControl('');
  textTitle: string;
  tagsList: any = []
  constructor(private router: Router,private questionService: QuestionService,private alertServc: AlertService, private authenticationService: AuthenticationService,) { }

  ngOnInit(): void {
    this.userdetails = JSON.parse(this.authenticationService.GetUserDetails())
    this.questionService.getInterests().subscribe(res => {
      if (res.success) {
        this.interests = res.data.interests
      }
    })
  }

  getValues(event: {
    isUserInput: any;
    source: { value: any; selected: any };
  }) {
    if (event.isUserInput) {
      if (event.source.selected === true) {
        this.tagsList.push(event.source.value);
      } else {
        console.log(event.source.value)
        this.tagsList = this.tagsList.filter(data => data != event.source.value);
      }
    }
  }

  submit() {

    let sentText = {
      "userid": this.userdetails?.userid,
      "content": this.editorText,
      "title": this.textTitle,
      "tags": ";" + this.tagsList.join(";") + ";"
    }
    this.questionService.postBlog(sentText).subscribe(res => {
      if (res.success) {
        this.alertServc.successAlert(appConstants.postBlog);
        this.router.navigate(['home'])
      }
    })

  }

}