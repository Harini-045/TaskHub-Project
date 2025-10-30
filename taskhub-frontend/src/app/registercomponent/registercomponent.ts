import { Component } from '@angular/core';
import { User } from '../model/user.model';
import { Userservice } from '../services/userservice';
import { Router } from '@angular/router';
import { Role } from '../model/role.model';
import { UserDTO } from '../model/userdto.model';
import { Roleservice } from '../services/roleservice';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-registercomponent',
  imports: [FormsModule, CommonModule],
  templateUrl: './registercomponent.html',
  styleUrl: './registercomponent.css',
})

export class Registercomponent {
  registerUser: UserDTO = {
  username: '',
  email: '',
  userPassword: '',
  roleIds: []
};

 roles: Role[] = [];
  selectedRoleId: number | null = null;
  errorMessage: string = '';
  successMessage: string = '';

  constructor(
    private userService: Userservice,
    private roleService: Roleservice,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadRoles();
  }

  loadRoles(): void {
    // Fetch all roles from the backend (use the public endpoint if needed)
    this.roleService.getAllRoles().subscribe({
      next: (response) => {
        // Optionally filter out 'Admin' role for public registration
        this.roles = response.filter((r: any) => r.roleName !== 'Admin');
      },
      error: (err) => {
        console.error('Error fetching roles:', err);
        this.errorMessage = 'Failed to load roles. Please try again later.';
      }
    });
  }

  handleFormSubmit(): void {
    if (!this.selectedRoleId) {
      this.errorMessage = 'Please select a role.';
      return;
    }

    console.log(this.registerUser);
    this.registerUser.roleIds = [this.selectedRoleId];
    console.log(this.registerUser.roleIds);

    this.userService.addAUser(this.registerUser).subscribe({
      next: (response) => {
        this.successMessage = 'Registration successful! Redirecting to login...';
        setTimeout(() => this.router.navigate(['/login']), 2000);
      },
      error: (err) => {
        console.error(err);
        this.errorMessage = 'Registration failed. Please try again.';
      }
    });
  }

}
