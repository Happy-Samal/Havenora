import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
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

const routes: Routes = [
  {path: '' , component:LoginComponent},
  { path:'admin-dashboard' ,
    component:AdminDashboardComponent,
    children:[
      {path:'', redirectTo:'dashboard',pathMatch:'full'},
      {path:'dashboard' , component:AdashboardComponent},
      {path:'book' , component:BookComponent},
      {path:'approval' , component:ApprovalComponent},
      {path:'rules' , component:RulesComponent},
      {path:'profile' , component:ProfileComponent},
    ]
  },
  { path:'employee-dashboard' ,
    component:EmployeeDashboardComponent,
    children:[
      {path:'', redirectTo:'dashboard',pathMatch:'full'},
      {path:'dashboard' , component:EdashaboardComponent},
      {path:'book' , component:BookComponent},
      {path:'mybookings' , component:MybookingComponent},
      {path:'rules' , component:RulesComponent},
      {path:'profile' , component:ProfileComponent},
    ]
  },
  { path:'**' , component:PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
