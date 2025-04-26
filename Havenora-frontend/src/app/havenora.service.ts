import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Employee } from 'src/models/Employee';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HavenoraService {

  constructor(private http:HttpClient){}

  empUrl = 'http://localhost:8080/havenoraEmployee'
  towerUrl = 'http://localhost:8080/havenoraTower'
  flatUrl = 'http://localhost:8080/havenoraFlat'
  roomUrl = 'http://localhost:8080/havenoraRoom'
  bookUrl = 'http://localhost:8080/havenoraBooking'

  loginValidation(obj:any):Observable<any>{
  return this.http.post(`${this.empUrl}/login`,obj)
  }

  displayTowerByLocation(location:any):Observable<any>{
    return this.http.get(`${this.towerUrl}/displayTowerByLocation/${location}`)
  }

  displayFlatByTower(towerId:number):Observable<any>{
    return this.http.get(`${this.flatUrl}/findByTower/${towerId}`)
  }

  displayRoomByFlat(flatId:number):Observable<any>{
    return this.http.get(`${this.roomUrl}/displayFlatById/${flatId}`)
  }

  bookSubmit(form:any):Observable<any>{
    return this.http.post(`${this.bookUrl}/createBooking`,form)
  }

  displayAllBooking():Observable<any>{
    return this.http.get(`${this.bookUrl}/displayAllBooking`)
  }

  displayAllRoom():Observable<any>{
    return this.http.get(`${this.roomUrl}/displayAll`)
  }

  displayEmpById(empId:any):Observable<any>{
    return this.http.get(`${this.empUrl}/displayEmployeeById/${empId}`)
  }
  displayTowerById(towerId:any):Observable<any>{
    return this.http.get(`${this.towerUrl}/displayTowerById/${towerId}`)
  }
  displayFlatById(flatId:any):Observable<any>{
    return this.http.get(`${this.flatUrl}/displayFlatById/${flatId}`)
  }
  displayRoomById(roomId:any):Observable<any>{
    return this.http.get(`${this.roomUrl}/displayRoomById/${roomId}`)
  }

  updateBookingById(bookingId:number, status:any):Observable<any>{
    return this.http.put(`${this.bookUrl}/updatebookingById/${bookingId}`,status)
  }

  displayBookingByEmployeeId(employeeId:number):Observable<any>{
    return this.http.get(`${this.bookUrl}/displayBookingsByEmployeeId/${employeeId}`)
  }

}
