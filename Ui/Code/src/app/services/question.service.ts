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
  postInterests(_interests):Observable<any> {
    return this.http.post(this.apiEndPoint+"/user/updateInterests",_interests)
  }
  postQuestion(_editorText):Observable<any> {
    return this.http.post(this.apiEndPoint+"/questions/askAquestion",_editorText)
  }
  postBlog(_blogText):Observable<any> {
    return this.http.post(this.apiEndPoint+"/blog/saveBlog",_blogText)
  }
  getNewsFeed(_unvcode):Observable<any> {
    
    return this.http.post(this.apiEndPoint+"/feed/getQuestionsFeed",{unvcode:_unvcode})
  }

  postEvent(_editorText):Observable<any> {
    return this.http.post(this.apiEndPoint+"/event/addAnEvent",_editorText)
  }
}
