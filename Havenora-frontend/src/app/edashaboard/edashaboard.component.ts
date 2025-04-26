import { Component } from '@angular/core';
import { Employee } from 'src/models/Employee';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edashaboard',
  templateUrl: './edashaboard.component.html',
  styleUrls: ['./edashaboard.component.css']
})
export class EdashaboardComponent {
  constructor(private auth:AuthService , private router:Router){}

    employee!:Employee

     ngOnInit(){
      this.employee= this.auth.getEmployee();
      if(this.employee == null){
        this.router.navigate(['**'])
      }
    }
}
