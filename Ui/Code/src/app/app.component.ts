import { Component, ChangeDetectorRef, AfterContentChecked  } from '@angular/core';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { AlertService } from './services/alert.service';
import { AuthenticationService } from './services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterContentChecked{
  loading$ = this.authenticationService.loading$;
  horizontalPosition: MatSnackBarHorizontalPosition = 'end';
  verticalPosition: MatSnackBarVerticalPosition = 'top';
  constructor(public authenticationService: AuthenticationService,private changeDetector: ChangeDetectorRef,private _snackBar: MatSnackBar,private alertSer:AlertService) {

    this.alertSer.alertSubject.subscribe((data:any)=>{
      this.openSnackBar(data,'Done')
    })

  }
  ngAfterContentChecked(): void {
    this.loading$= this.authenticationService.loading$;
    this.changeDetector.detectChanges();
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action,{
      duration: 5 * 1000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition
    });
  }
}
