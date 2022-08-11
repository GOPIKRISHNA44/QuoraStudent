import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BlogComponent } from './blog/blog.component';
import { AuthGuard } from './guards/auth.guards';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { NewsfeedComponent } from './newsfeed/newsfeed.component';
import { QuestionAnswerComponent } from './question-answer/question-answer.component';

const routes: Routes = [

  { path: 'login', component: LoginComponent, canActivate: [AuthGuard], data: { "path": "/login" } },
  {
    path: 'home', component: HomeComponent,
    children: [
      {
        path: 'question', component: QuestionAnswerComponent, canActivate: [AuthGuard], data: { "path": "/home/question" }
      },
      {
        path: '', component: NewsfeedComponent, canActivate: [AuthGuard], data: { "path": "/home/feed" }
      },
      {
        path: 'blog', component: BlogComponent, canActivate: [AuthGuard], data: { "path": "/home/blog" }
      },
    ]
  },
  { path: 'question/:eqid/:ctype', component: QuestionAnswerComponent },
  { path: '**', redirectTo: 'home' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes), RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
