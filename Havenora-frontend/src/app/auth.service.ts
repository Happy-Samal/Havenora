import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  // Save employee in localStorage
  saveEmployee(employee: any) {
    localStorage.setItem('iemployee', JSON.stringify(employee));
  }

  // Retrieve employee from localStorage
  getEmployee():any {
    const employee = localStorage.getItem('iemployee');
    return employee ? JSON.parse(employee) : null;
  }

  // Clear employee (on logout)
  clearEmployee() {
    localStorage.removeItem('iemployee');
  }
}
