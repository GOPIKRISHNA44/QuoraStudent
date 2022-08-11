import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material/material.module';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthenticationService } from './services/authentication.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { SignupDialogComponent } from './signup-dialog/signup-dialog.component';
import { AuthGuard } from './guards/auth.guards';
import { DatePipe } from '@angular/common';
import { InterestsDialogComponent } from './interests-dialog/interests-dialog.component';
import { QuestionTextboxComponent } from './question-textbox/question-textbox.component';
import { AskQuestionDialogComponent } from './ask-question-dialog/ask-question-dialog.component';
import { QuillModule } from 'ngx-quill';
import { QuillComponent } from './quill/quill.component';
import { NetworkInterceptor } from './services/network.interceptor';
import { NewsfeedComponent } from './newsfeed/newsfeed.component';
import { QuestionAnswerComponent } from './question-answer/question-answer.component';
import { HeaderComponent } from './header/header.component';
import { BlogComponent } from './blog/blog.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    SignupDialogComponent,
    InterestsDialogComponent,
    QuestionTextboxComponent,
    AskQuestionDialogComponent,
    QuillComponent,
    NewsfeedComponent,
    QuestionAnswerComponent,
    HeaderComponent,
    BlogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    QuillModule.forRoot()
  ],
  providers: [AuthenticationService,AuthGuard,DatePipe,{
    provide: HTTP_INTERCEPTORS,
    useClass: NetworkInterceptor,
    multi: true,
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
