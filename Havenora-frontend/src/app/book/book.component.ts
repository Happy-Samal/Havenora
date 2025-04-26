import { Component, ViewChild } from '@angular/core';
import { HavenoraService } from '../havenora.service';
import { Tower } from 'src/models/Tower';
import { Flat } from 'src/models/Flat';
import { Room } from 'src/models/Room';
import { Booking } from 'src/models/Booking';
import { AuthService } from '../auth.service';
import { Employee } from 'src/models/Employee';
import { NgForm } from '@angular/forms';
import { formatDate } from '@angular/common';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent {
  constructor(private ser:HavenoraService, private auth:AuthService){}
  locations = ["Chennai","Hyderabad","Mumbai","Pune"];
  towers: Tower[] = [];
  flats: Flat[] = [];
  rooms: Room[] = [];
  booking!:Booking;
  employe!:Employee;

  ngOnInit(){
    this.employe =this.auth.getEmployee();
  }
  @ViewChild('book') form!:NgForm

  bookSubmit(book:any){
    this.booking = {...book.value};
    let todayDate = formatDate(new Date(),'yyyy-MM-dd','en_US');
    let fromDate  = formatDate(book.value.fromDate,'yyyy-MM-dd','en_US');
    let toDate  = formatDate(book.value.toDate,'yyyy-MM-dd','en_US');

    if(todayDate>fromDate || todayDate > toDate || fromDate > toDate){
      alert("You can't go to past 😂")
     }else{
    this.booking.employeeId = this.employe.employeeId;
    this.ser.bookSubmit(this.booking).subscribe(Response=>{
      alert("Booking successfully!")
      this.form.reset();
    },Error=>{
      alert("Booking failure!");
      this.form.reset();
      console.log(Error);
    });
    }
  }


  fetchTowerByLocation(location:string){
    this.ser.displayTowerByLocation(location).subscribe(Response=>{
      this.towers = Response;
    },Error=>{
      console.log(Error);
    });
  }

  fetchFlatByTower(towerId:number){
    this.ser.displayFlatByTower(towerId).subscribe(Response=>{
      this.flats = Response;
    },Error=>{
      console.log(Error);
    });
  }
  fetchRoomByFlat(flatId:number){
    this.ser.displayRoomByFlat(flatId).subscribe(Response=>{
      this.rooms = Response;
    },Error=>{
      console.log(Error);
    });
  }
}
