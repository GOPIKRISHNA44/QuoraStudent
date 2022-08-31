import { Component, OnInit } from '@angular/core';
import { UserDetails } from '../models/auth.model';
import { AuthenticationService } from '../services/authentication.service';
import { QuestionService } from '../services/question.service';

@Component({
  selector: 'app-leaderboard',
  templateUrl: './leaderboard.component.html',
  styleUrls: ['./leaderboard.component.css']
})
export class LeaderboardComponent implements OnInit {
  userdetails: UserDetails;
  dataSource:any
  constructor(private authenticationService:AuthenticationService,private questionService:QuestionService) { }

  ngOnInit(): void {
    this.userdetails = JSON.parse(this.authenticationService.GetUserDetails())
    this.myLeaderBoard()
  }
  myLeaderBoard(){
    let unvcode=this.userdetails.universitycode.toString();
   this.questionService.leaderboard(unvcode).subscribe(res => {
    if (res.success) {
      this.dataSource=res
    }})
  }
}
