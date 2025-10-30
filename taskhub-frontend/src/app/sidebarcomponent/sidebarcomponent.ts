import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { Authservice } from '../services/authservice';
import { Role } from '../model/role.model';

@Component({
  selector: 'app-sidebarcomponent',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './sidebarcomponent.html',
  styleUrl: './sidebarcomponent.css',
})
export class Sidebarcomponent {
role: Role[] = [] // e.g. 'Admin' | 'Manager' | 'User'

  // Define all functionalities for different roles
  adminFunctions = [
    { name: 'Dashboard', icon: 'bi-speedometer2', route: '/dashboard' },
    { name: 'All Tasks', icon: 'bi-card-checklist', route: '/admin-task' },
    { name: 'Team Management', icon: 'bi-people', route: '/team' },
    { name: 'User Management', icon: 'bi-person', route: '/user' },
    { name: 'Logout', icon: 'bi-box-arrow-right', route: '/logout' }
  ];

  managerFunctions = [
    { name: 'Dashboard', icon: 'bi-speedometer2', route: '/dashboard' },
    { name: 'All Tasks', icon: 'bi-card-checklist', route: '/admin-task' },
    { name: 'Team Management', icon: 'bi-people', route: '/team' },
    { name: 'Logout', icon: 'bi-box-arrow-right', route: '/logout' }
  ];

  userFunctions = [
    { name: 'Dashboard', icon: 'bi-speedometer2', route: '/dashboard' },
    { name: 'My Tasks', icon: 'bi-card-checklist', route: '/user-task' },
    { name: 'Logout', icon: 'bi-box-arrow-right', route: '/logout' }
  ];

  functionalities: any[] = [];

  constructor(private router: Router, private authService: Authservice) {}

  ngOnInit() {
    // You can get the role from localStorage or auth service
   this.role = this.authService.retrieveRole() ?? [];


    console.log(this.role);

    // Choose sidebar menu based on role
    if (this.authService.isAdmin()) {
      this.functionalities = this.adminFunctions;
    } else if (this.authService.isManager()) {
      this.functionalities = this.managerFunctions;
    } else {
      this.functionalities = this.userFunctions;
    }
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/login']);
  }

}
