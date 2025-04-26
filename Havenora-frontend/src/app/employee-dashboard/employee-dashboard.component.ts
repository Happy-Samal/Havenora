import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Employee } from 'src/models/Employee';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-dashboard',
  templateUrl: './employee-dashboard.component.html',
  styleUrls: ['./employee-dashboard.component.css']
})
export class EmployeeDashboardComponent {
   constructor(private router:Router, private auth:AuthService){}

   employee!:Employee

   activeBtn:string = ''
   ngOnInit(){
     this.activeBtn ='dashboard'
     this.employee= this.auth.getEmployee();
     if(this.employee == null){
      this.router.navigate(['**'])
    }
   }
   dashboardClick(btnName:string){
     this.activeBtn=btnName;
     this.router.navigate(['/employee-dashboard/dashboard'])
   }
   bookClick(btnName:string){
     this.activeBtn=btnName;
     this.router.navigate(['/employee-dashboard/book'])
   }
   myBookingClick(btnName:string){
     this.activeBtn=btnName;
     this.router.navigate(['/employee-dashboard/mybookings'])
   }
   rulesClick(btnName:string){
     this.activeBtn=btnName;
     this.router.navigate(['/employee-dashboard/rules'])
   }
   profileClick(btnName:string){
     this.activeBtn=btnName;
     this.router.navigate(['/employee-dashboard/profile'])

   }

}
