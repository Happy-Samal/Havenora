import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Employee } from 'src/models/Employee';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent {
   constructor(private router:Router, private auth:AuthService){}



  activeBtn:string = ''
  employee!:Employee

  ngOnInit(){
    this.activeBtn ='dashboard'
    this.employee= this.auth.getEmployee();
    if(this.employee == null){
      this.router.navigate(['**'])
    }
  }
  dashboardClick(btnName:string){
    this.activeBtn=btnName;
    this.router.navigate(['/admin-dashboard/dashboard'])
  }
  bookClick(btnName:string){
    this.activeBtn=btnName;
    this.router.navigate(['/admin-dashboard/book'])
  }

  approvalClick(btnName:string){
    this.activeBtn=btnName;
    this.router.navigate(['/admin-dashboard/approval'])
  }
  rulesClick(btnName:string){
    this.activeBtn=btnName;
    this.router.navigate(['/admin-dashboard/rules'])
  }
  profileClick(btnName:string){
    this.activeBtn=btnName;
    this.router.navigate(['/admin-dashboard/profile'])

  }
}
