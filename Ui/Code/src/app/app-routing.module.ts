import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './guards/auth.guards';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard], data: { "path": "/home" } },
  { path: 'login', component: LoginComponent , canActivate: [AuthGuard], data: { "path": "/login" } },
  // otherwise redirect to home
  { path: '**', redirectTo: 'home' },
  { path: 'test', component: HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
