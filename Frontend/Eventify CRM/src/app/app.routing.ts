import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './authentication/login/login.component';
import { RegisterComponent } from './authentication/register/register.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { EventsComponent } from './pages/events/events.component';
import { TasksComponent } from './pages/tasks/tasks.component';
import { ExploreComponent } from './pages/explore/explore.component';
import { VendorsComponent } from './pages/vendors/vendors.component';
import { FeedbacksComponent } from './pages/feedbacks/feedbacks.component';


const routes: Routes =[
    { path: '', redirectTo: 'index', pathMatch: 'full' },
    { path: 'index',                component: HomeComponent },
    { path: '',     component: HomeComponent },
    { path: 'login',       component: LoginComponent },
    { path: 'register',       component: RegisterComponent },
    { path: 'profile', component: ProfileComponent},
    { path: 'dashboard', component: DashboardComponent},
    { path: 'events', component: EventsComponent},
    { path: 'tasks', component: TasksComponent},
    { path: 'explore', component: ExploreComponent},
    { path: 'vendors', component: VendorsComponent},
    { path: 'feedbacks', component: FeedbacksComponent }

];

@NgModule({
    imports: [
        CommonModule,
        BrowserModule,
        RouterModule.forRoot(routes)
    ],
    exports: [RouterModule
    ],
})
export class AppRoutingModule { }
