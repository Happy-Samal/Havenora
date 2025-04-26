import { Component } from '@angular/core';
import { HavenoraService } from '../havenora.service';
import { Employee } from 'src/models/Employee';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-adashboard',
  templateUrl: './adashboard.component.html',
  styleUrls: ['./adashboard.component.css']
})
export class AdashboardComponent {
  constructor(private ser:HavenoraService, private auth:AuthService , private router:Router){}
  totalBookings:any
  pendingBooking = 0;
  totalAvailableRooms:any
  employee!:Employee

  async ngOnInit(){
    this.employee= this.auth.getEmployee();
    if(this.employee == null){
      this.router.navigate(['**'])
    }
    try{
      let response = await this.ser.displayAllBooking().toPromise();
      this.totalBookings = response.length;
      for(let i of response){
        if(i.status == "Pending"){
          this.pendingBooking++;
        }
      }
      let res = await this.ser.displayAllRoom().toPromise();
      this.totalAvailableRooms =  res.length - this.totalBookings;
    }catch(error){
      console.log(error);
    }
  }

}
