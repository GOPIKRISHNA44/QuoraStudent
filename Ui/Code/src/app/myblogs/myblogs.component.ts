import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { appConstants } from '../constants/alert.constants';
import { QuillConfiguration, Title } from '../constants/title.constants';
import { UserDetails } from '../models/auth.model';
import { AlertService } from '../services/alert.service';
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
  tags:FormControl
  textTitle: string;
  tagsList: any = []
  tagsId = []
  myData: any
  data: any
  selected=[]
  constructor(private router: Router, private alertServc: AlertService,private spinnerService: SpinnerService, private route: ActivatedRoute, private questionService: QuestionService, private authenticationService: AuthenticationService,) { }

  ngOnInit(): void {
    this.userdetails = JSON.parse(this.authenticationService.GetUserDetails())
    this.questionService.getInterests().subscribe(res => {
      if (res.success) {
        this.interests = res.data.interests
        this.questionService.editBlogDetails$.subscribe((value) => {
          this.data = value
          this.editorText = value.content ? value.content : ''
          this.textTitle = value.title ? value.title : ''
          this.tagsId = value?.tags?.split(';').filter((a) => a)
          this.tagsId?.filter((id) => {
           this.interests?.map(item => {
               if(parseInt(id) == item.id){
                this.selected.push(item.id)
               }
            })
          })
          this.tagsList=this.selected
        })
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
      "bid": this.data?.bid,
      "content": this.editorText,
      "title": this.textTitle,
      "tags": ";" + this.tagsList.join(";") + ";"
    }
    this.questionService.updateBlog(sentText).subscribe(res => {
      if (res.success) {
        this.alertServc.successAlert(appConstants.updateBlog);
          
        this.router.navigate(['/home/myBlogs'])
      }
    })
  }
}
