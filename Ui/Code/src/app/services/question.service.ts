import { HttpClient, HttpParams } from '@angular/common/http';
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

  private blogDetails = new BehaviorSubject<any>([]);
  public readonly blogDetails$ = this.blogDetails.asObservable();

  private ctype$ = new BehaviorSubject<any>('');
  requiredCtype$ = this.ctype$.asObservable();

  private editBlogDetails = new BehaviorSubject<any>([]);
  public readonly editBlogDetails$ = this.editBlogDetails.asObservable();

  getInterests(): Observable<any> {
    return this.http.get(this.apiEndPoint + "/info/getInterests")
  }
  postInterests(_interests): Observable<any> {
    return this.http.post(this.apiEndPoint + "/user/updateInterests", _interests)
  }
  postQuestion(_editorText): Observable<any> {
    return this.http.post(this.apiEndPoint + "/questions/askAquestion", _editorText)
  }
  editUpdateQuestion(_editorText): Observable<any> {
    return this.http.post(this.apiEndPoint + "/questions/updateAQuestion", _editorText)
  }
  postBlog(_blogText): Observable<any> {
    return this.http.post(this.apiEndPoint + "/blog/saveBlog", _blogText)
  }
  updateBlog(_blogText): Observable<any> {
    return this.http.post(this.apiEndPoint + "/blog/updateBlog", _blogText)
  }


  getQuestionOrEventFeed(_details): Observable<any> {

    return this.http.post(this.apiEndPoint + "/feed/getQuestionsOrEventFeed", _details)
  }
  getQuestionDetails(_questionDetails): Observable<any> {

    return this.http.post(this.apiEndPoint + "/questions/getQuestion", _questionDetails)
  }


  postEvent(_editorText): Observable<any> {
    return this.http.post(this.apiEndPoint + "/event/addAnEvent", _editorText)
  }
  updateAnEvent(_editorText): Observable<any> {
    return this.http.post(this.apiEndPoint + "/event/updateAnEvent", _editorText)
  }
  
  getAnswers(_details): Observable<any> {
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
  postAnswer(_details): Observable<any> {
    return this.http.post(this.apiEndPoint + "/answer/addAnswer", _details)
  }
  updateLikeButton(_details): Observable<any> {

    return this.http.post(this.apiEndPoint + "/questions/updwnvt", _details)
  }
  deleteComment(_commentDetails): Observable<any> {
    return this.http.post(this.apiEndPoint + "/comments/deleteComment", _commentDetails)
  }
  deleteAnswer(_details): Observable<any> {
    return this.http.post(this.apiEndPoint + "/answer/deleteAnswer", _details)
  }
  deleteQuestion(_details): Observable<any> {
    return this.http.post(this.apiEndPoint + "/questions/deleteQuestion", _details)
  }
  deleteBlog(_details): Observable<any> {
    return this.http.post(this.apiEndPoint + "/blog/deleteBlog", _details)
  }
  getBlogFeed(_details): Observable<any> {

    return this.http.post(this.apiEndPoint + "/feed/getBlogFeed", _details)
  }
  getMyQuestions(_details): Observable<any> {

    return this.http.post(this.apiEndPoint + "/feed/getMyQuestionsOrEvents", _details)
  }
  getMyBlogs(_details): Observable<any> {

    return this.http.post(this.apiEndPoint + "/feed/getMyBlogs", _details)
  }
  getTagRelatedQuesOrEvents(_details): Observable<any> {

    return this.http.post(this.apiEndPoint + "/feed/getTagRelatedQuesOrEvents", _details)
  }
  getTotalNumberOfQuestions(unvcode): Observable<any> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("unvcode", unvcode);
    return this.http.get(this.apiEndPoint + "/questions/getQuestionsCount", { params: queryParams })
  }
  getTopLikedBlogs(_details): Observable<any> {

    return this.http.post(this.apiEndPoint + "/feed/getTopLikedBlogs", _details)
  }
  changeAvatar(_details): Observable<any> {

    return this.http.post(this.apiEndPoint + "/user/updateAvatar", _details)
  }
  deleteEvent(_details): Observable<any> {
    return this.http.post(this.apiEndPoint + "/event/deleteAnEvent", _details)
  }
  leaderboard(unvcode): Observable<any> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("unvcode", unvcode);
    return this.http.get(this.apiEndPoint + "/info/getLeaderboard", { params: queryParams })
  }
  setCtype(ctype) {
    this.ctype$.next(ctype);
  }
  setBlogDetails(data){
    this.blogDetails.next(data)
  }

  
  setEditBlogDetails(data){
    this.editBlogDetails.next(data)
  }
}
