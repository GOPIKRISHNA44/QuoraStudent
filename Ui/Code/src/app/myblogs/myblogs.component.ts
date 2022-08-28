import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { QuillConfiguration, Title } from '../constants/title.constants';
import { UserDetails } from '../models/auth.model';
import { AuthenticationService } from '../services/authentication.service';
import { QuestionService } from '../services/question.service';
import { SpinnerService } from '../services/spinner.service';

@Component({
  selector: 'app-myblogs',
  templateUrl: './myblogs.component.html',
  styleUrls: ['./myblogs.component.css']
})
export class MyblogsComponent implements OnInit {
  quillConfiguration = QuillConfiguration
  placeholder = Title.blogPlaceholder
  editorText: string;
  userdetails: UserDetails;
  interests = []
  tags = new FormControl('');
  textTitle: string;
  tagsList: any = []
  myData: any
  constructor(private router: Router,private spinnerService:SpinnerService,private route: ActivatedRoute, private questionService: QuestionService, private authenticationService: AuthenticationService,) { }

  ngOnInit(): void {
    this.userdetails = JSON.parse(this.authenticationService.GetUserDetails())
    this.questionService.getInterests().subscribe(res => {
      if (res.success) {
        this.interests = res.data.interests
      }
    })
  this.myBlogs()
  }
  myBlogs(){
    let details = {
      "userid": this.userdetails.userid,
      
    }
    this.questionService.getMyQuestions(details).subscribe(res => {
      if (res.success) {
        this.myData = res.data
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
  submit(){
    let sentText = {
      "userid": this.userdetails?.userid,
      "content": this.editorText,
      "title": this.textTitle,
      "tags": ";" + this.tagsList.join(";") + ";"
    }
    this.questionService.postBlog(sentText).subscribe(res => {
      if (res.success) {
        this.router.navigate(['home/blog'])
      }
    })
  }
}
