import { Component, OnInit } from '@angular/core';
import { UserDetails } from '../models/auth.model';
import { AuthenticationService } from '../services/authentication.service';
import { QuestionService } from '../services/question.service';

@Component({
  selector: 'app-newsfeed',
  templateUrl: './newsfeed.component.html',
  styleUrls: ['./newsfeed.component.css']
})
export class NewsfeedComponent implements OnInit {
  userdetails: UserDetails;
  data: any;
  constructor(private questionService: QuestionService, private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    this.userdetails = JSON.parse(this.authenticationService.GetUserDetails())

    this.questionService.getNewsFeed(this.userdetails.universitycode).subscribe(res => {
      if (res.success) {
        this.data = res.data
      }
    })

  }
  likeButton(){
    this.data.upvotes++
  }

}
