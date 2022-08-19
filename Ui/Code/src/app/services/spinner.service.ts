import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { environment } from 'src/environments/environment';
import 'rxjs/add/operator/map';
import { BehaviorSubject, Subject } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class SpinnerService {
    showLoader: boolean = true;

    constructor() { }

    disableLoader() {
        this.showLoader = false;
    }
    enableLoader() {
        this.showLoader = true;
    }
    getLoaderStatus() {
        return this.showLoader;
    }



}
