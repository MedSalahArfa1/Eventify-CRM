import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; // this is needed!
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app.routing';
import { ComponentsModule } from './components/components.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppComponent } from './app.component';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './authentication/login/login.component';
import { RegisterComponent } from './authentication/register/register.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { HomeComponent } from './pages/home/home.component';
import { EventsComponent } from './pages/events/events.component';
import { TasksComponent } from './pages/tasks/tasks.component';
import { ExploreComponent } from './pages/explore/explore.component';
import { AuthService } from './services/auth.service';
import { VendorsComponent } from './pages/vendors/vendors.component';
import { FeedbackPopupComponent } from './components/feedback-popup/feedback-popup.component';
import { FeedbacksComponent } from './pages/feedbacks/feedbacks.component';

@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        RegisterComponent,
        ProfileComponent,
        HomeComponent,
        DashboardComponent,
        EventsComponent,
        TasksComponent,
        ExploreComponent,
        VendorsComponent,
        FeedbackPopupComponent,
        FeedbacksComponent
    ],
    imports: [
        BrowserAnimationsModule,
        NgbModule,
        FormsModule,
        RouterModule,
        AppRoutingModule,
        ComponentsModule,
        HttpClientModule,
        CommonModule,
        ReactiveFormsModule,
        
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule { }
