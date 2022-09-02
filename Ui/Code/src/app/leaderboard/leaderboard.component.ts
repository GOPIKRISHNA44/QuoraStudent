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
  dataSource: any
  displayedColumns: string[] = ['position', 'username', 'likes'];
  constructor(private authenticationService: AuthenticationService, private questionService: QuestionService) { }

  ngOnInit(): void {
    this.userdetails = JSON.parse(this.authenticationService.GetUserDetails())
    this.myLeaderBoard()
  }
  myLeaderBoard() {
    let unvcode = this.userdetails.universitycode.toString();
    this.questionService.leaderboard(unvcode).subscribe(res => {
      if (res.success) {
        this.dataSource = res?.data
        let a= [];
        this.dataSource.forEach(element => {
          if(element["numberoflikes"]>0)
          {
            a.push(element);
          }
        });
        this.dataSource = a;
      }
    })
  }
}
