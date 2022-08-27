import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BlogComponent } from './blog/blog.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { AuthGuard } from './guards/auth.guards';
import { HomeComponent } from './home/home.component';
import { IndividualBlogComponent } from './individual-blog/individual-blog.component';
import { LoginComponent } from './login/login.component';
import { MyblogsComponent } from './myblogs/myblogs.component';
import { MyquestionsoreventsComponent } from './myquestionsorevents/myquestionsorevents.component';
import { NewsfeedComponent } from './newsfeed/newsfeed.component';
import { QuestionAnswerComponent } from './question-answer/question-answer.component';
import { ViewBlogComponent } from './view-blog/view-blog.component';

const routes: Routes = [

  { path: 'login', component: LoginComponent, canActivate: [AuthGuard], data: { "path": "/login" } },
  {
    path: 'home', component: HomeComponent,
    children: [
      {
        path: 'question', component: QuestionAnswerComponent, canActivate: [AuthGuard], data: { "path": "/home/question" }
      },
      {
        path: 'viewBlog', component: IndividualBlogComponent, canActivate: [AuthGuard], data: { "path": "/home/viewBlog" }
      },
      {
        path: '', component: NewsfeedComponent, canActivate: [AuthGuard], data: { "path": "/home/feed" }
      },
      {
        path: 'blog', component: BlogComponent, canActivate: [AuthGuard], data: { "path": "/home/blog" }
      },
      {
        path: 'myQuestionOrEvents', component: MyquestionsoreventsComponent, data: { "path": "/home/myQuestionOrEvents" }
      },
      {
        path: 'myBlogs', component: ViewBlogComponent, data: { "path": "/home/myBlogs" }
      },
    ]
  },

  { path: 'question/:eqid/:ctype', component: QuestionAnswerComponent },
  { path: 'viewBlog/:bid/:ctype', component: IndividualBlogComponent },
  { path: 'changePassword', component: ChangePasswordComponent, canActivate: [AuthGuard], data: { "path": "/changePassword" } },
  { path: '**', redirectTo: 'home' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes), RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
