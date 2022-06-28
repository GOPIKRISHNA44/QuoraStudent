import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map'
import { environment } from 'src/environments/environment';
import { LoginDetails } from '../models/auth.model';
import { resolve } from 'dns';

@Injectable({
    providedIn: 'root'
})
export class HttpService {



    constructor(private https: Http) { }

    public post(URL, body): Observable<any> {

        return this.https.post(URL, body);
    }

    public get(URL,body):Observable<any>
    {
        return this.https.post(URL, body);
    }

}
