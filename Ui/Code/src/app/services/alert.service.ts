import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { environment } from 'src/environments/environment';
import 'rxjs/add/operator/map';
import { BehaviorSubject, Subject } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class AlertService {
    apiEndPoint = environment.apiEndPoint;
    
    content: any;  
    public alertSubject = new Subject();
      
    constructor(private http: HttpClient) { }
    successAlert(Msg) {
        this.alertSubject.next(Msg);
    }

}
