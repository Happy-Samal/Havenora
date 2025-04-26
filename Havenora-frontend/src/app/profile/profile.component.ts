import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Employee } from 'src/models/Employee';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
    constructor(private auth:AuthService , private router:Router){}
    employee!:Employee

    ngOnInit(){
      this.employee= this.auth.getEmployee();
    }
    logout(){
      this.auth.clearEmployee();
      // window.location.reload()
      this.router.navigate(['/']);
    }
}
