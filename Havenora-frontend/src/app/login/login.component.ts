

import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Employee } from 'src/models/Employee';
import { Route, Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { HavenoraService } from '../havenora.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

    constructor(private ser:HavenoraService,private router:Router , private auth:AuthService){}

    @ViewChild('login')form!:NgForm
    responseStatus = ''
    errorStatus = ''

    submit(form:any){
      this.ser.loginValidation(form.value).subscribe(Response =>{
        this.responseStatus=Response;

        this.auth.clearEmployee();
        this.auth.saveEmployee(Response);
        if(Response.name == 'Admin'){
          this.router.navigate(['/admin-dashboard']);
        }else{
          this.router.navigate(['/employee-dashboard']);
        }
      },Error=>{
          this.errorStatus=Error;
          alert("loign failure");
          this.form.reset()
      })
    }

}
