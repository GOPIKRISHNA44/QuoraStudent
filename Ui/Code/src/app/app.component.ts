import { Component, ChangeDetectorRef, AfterContentChecked  } from '@angular/core';
import { AuthenticationService } from './services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterContentChecked{
  loading$ = this.authenticationService.loading$;
  constructor(public authenticationService: AuthenticationService,private changeDetector: ChangeDetectorRef,) {}
  ngAfterContentChecked(): void {
    this.loading$= this.authenticationService.loading$;
    this.changeDetector.detectChanges();
  }
}
