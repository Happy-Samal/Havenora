import { Component } from '@angular/core';
import { HavenoraService } from '../havenora.service';
import { Approval } from 'src/models/Approval';
import { Booking } from 'src/models/Booking';
import { Employee } from 'src/models/Employee';
import { Tower } from 'src/models/Tower';
import { Flat } from 'src/models/Flat';
import { Room } from 'src/models/Room';

@Component({
  selector: 'app-approval',
  templateUrl: './approval.component.html',
  styleUrls: ['./approval.component.css']
})
export class ApprovalComponent {


  constructor(private ser:HavenoraService){}
  approval!:Approval[];
  bookings!:Booking[];
  employee!:Employee;
  tower!:Tower;
  flat!:Flat;
  room!:Room;


  async displayEmployee(employeeId:any){
    try{
      let response = await this.ser.displayEmpById(employeeId).toPromise();
      return response.name;
    }catch(error){
      console.log(error);
    }

  }
  async displayTower(towerId:any){
    try{
      let response = await this.ser.displayTowerById(towerId).toPromise();
      return response.name;
    }catch(error){
      console.log(error);
    }
  }
  async displayFlat(flatId:any){
    try{
    let response =   await this.ser.displayFlatById(flatId).toPromise();
    return response.flatNo;
    }catch(error){
      console.log(error);
    }
  }
  async displayRoom(roomId:any){
    try{
      let response =   await this.ser.displayRoomById(roomId).toPromise();
      return response.roomNo;
      }catch(error){
        console.log(error);
      }
  }

  async ngOnInit(){
    try{
      let response = await this.ser.displayAllBooking().toPromise();
      this.bookings = response;
      this.approval = [];
      for(let el of this.bookings){
        let app = new Approval();
        app.bookingId = el.bookingId;
        app.location = el.location;
        app.fromDate = el.fromDate;
        app.toDate = el.toDate;
        app.status = el.status;
        app.employeeName = await this.displayEmployee(el.employeeId);
        app.towerName= await this.displayTower(el.towerId);
        app.flatNo= await this.displayFlat(el.flatId);
        app.roomNo= await this.displayRoom(el.roomId);
        this.approval.push(app);
      }
    }catch(error){
      console.log(error);
    }
  }
  rejected(bookingId: number) {
    this.ser.updateBookingById(bookingId,"Rejected").subscribe(Response=>{
      window.location.reload();
      console.log(Response);
    },Error=>{
      console.log(Error);
    })
  }
  async accepted(bookingId: number) {
    this.ser.updateBookingById(bookingId,"Accepted").subscribe(Response=>{
      window.location.reload();
      console.log(Response);
    },Error=>{
      console.log(Error);
    })

  }
}
