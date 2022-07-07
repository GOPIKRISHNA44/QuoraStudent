import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { environment } from 'src/environments/environment';
import 'rxjs/add/operator/map';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  apiEndPoint=environment.apiEndPoint;
  constructor(private http: HttpClient) { }

  getInterests(): Observable<any> {
    return this.http.get(this.apiEndPoint+"/info/getInterests")
  }
  postQuestion(editorText):Observable<any> {
    return this.http.get(this.apiEndPoint+"/questions/askAquestion",editorText)
  }
}