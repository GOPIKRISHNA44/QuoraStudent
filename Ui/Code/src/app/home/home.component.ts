import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private route: ActivatedRoute,
    private router: Router, private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
  }
  logOut() {
    this.authenticationService.logout().subscribe(res => {
      if (res.success) {
        localStorage.removeItem('token');
        this.router.navigate(['/'])
      } else {
        alert('Reason:' + res?.reason)
      }
    },
      error => {
        alert('Error occured with message ' + error?.message)
      })
  }
}
