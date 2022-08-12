import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { environment } from 'src/environments/environment';
import 'rxjs/add/operator/map';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  apiEndPoint = environment.apiEndPoint;
  constructor(private http: HttpClient) { }

  private questiondetails = new BehaviorSubject([]);
  public readonly questiondetails$ = this.questiondetails.asObservable();

  getInterests(): Observable<any> {
    return this.http.get(this.apiEndPoint + "/info/getInterests")
  }
  postInterests(_interests): Observable<any> {
    return this.http.post(this.apiEndPoint + "/user/updateInterests", _interests)
  }
  postQuestion(_editorText): Observable<any> {
    return this.http.post(this.apiEndPoint + "/questions/askAquestion", _editorText)
  }
  postBlog(_blogText): Observable<any> {
    return this.http.post(this.apiEndPoint + "/blog/saveBlog", _blogText)
  }
  getNewsFeed(_unvcode): Observable<any> {

    return this.http.post(this.apiEndPoint + "/feed/getQuestionsFeed", { unvcode: _unvcode })
  }
  getQuestionDetails(_questionDetails): Observable<any> {

    return this.http.post(this.apiEndPoint + "/questions/getQuestion", _questionDetails)
  }


  postEvent(_editorText):Observable<any> {
    return this.http.post(this.apiEndPoint+"/event/addAnEvent",_editorText)
  }

  getAnswers(_details):Observable<any>{
    return this.http.post(this.apiEndPoint + "/answer/getAnswersForQuestionOrEntity", _details)
  }


  updateQuestion(_questionDetails) {
    this.questiondetails.next(_questionDetails)
  }
  getComments(_commentDetails): Observable<any> {

    return this.http.post(this.apiEndPoint + "/comments/getCommentsList", _commentDetails)
  }
  sendComments(_commentDetails): Observable<any> {

    return this.http.post(this.apiEndPoint + "/comments/addComment", _commentDetails)
  }
  postAnswer(_details): Observable<any>{
    return this.http.post(this.apiEndPoint + "/answer/addAnswer", _details)
  }
  updateLikeButton(_details): Observable<any>{
    return this.http.post(this.apiEndPoint + "/questions/updwnvt", _details)
  }

}
