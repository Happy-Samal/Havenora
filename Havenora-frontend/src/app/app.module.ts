import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './login/login.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { EmployeeDashboardComponent } from './employee-dashboard/employee-dashboard.component';
import { AdashboardComponent } from './adashboard/adashboard.component';
import { BookComponent } from './book/book.component';
import { ApprovalComponent } from './approval/approval.component';
import { RulesComponent } from './rules/rules.component';
import { ProfileComponent } from './profile/profile.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { EdashaboardComponent } from './edashaboard/edashaboard.component';
import { MybookingComponent } from './mybooking/mybooking.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    FooterComponent,
    LoginComponent,
    AdminDashboardComponent,
    EmployeeDashboardComponent,
    AdashboardComponent,
    BookComponent,
    ApprovalComponent,
    RulesComponent,
    ProfileComponent,
    PageNotFoundComponent,
    EdashaboardComponent,
    MybookingComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
