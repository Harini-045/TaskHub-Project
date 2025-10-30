import { Component } from '@angular/core';
import { User } from '../model/user.model';
import { UserDTO } from '../model/userdto.model';
import { Userservice } from '../services/userservice';
import { Authservice } from '../services/authservice';
import { FormsModule } from '@angular/forms';
import { Roleservice } from '../services/roleservice';
import { Role } from '../model/role.model';

@Component({
  selector: 'app-userdashboard',
  imports: [FormsModule],
  templateUrl: './userdashboard.html',
  styleUrl: './userdashboard.css',
})
export class Userdashboard {

  allUsers: User[] = [];
  allRoles: Role[] = [];
  userModalOpen: boolean = false;
  isEditMode: boolean = false;
  editUserData: UserDTO = { username: '', email: '', userPassword: '', roleIds: [] };
  currentEditUserId: number | null = null;

  constructor(
    private userService: Userservice,
    private roleService: Roleservice // Optional, if needed for fetching all roles
  ) {}

  ngOnInit() {
    this.loadRoles();
    this.loadUsers();
  }

  loadUsers() {
    this.userService.getAllUsers().subscribe({
      next: (users) => { this.allUsers = users; },
      error: (err) => console.error('Failed to load users:', err)
    });
  }

  loadRoles() {
    // If you fetch available roles from backend
    this.roleService.getAllRoles().subscribe({
      next: (roles) => { this.allRoles = roles; },
      error: (err) => console.error('Failed to load roles:', err)
    });
    // Or, if you just hardcode for now:
    // this.allRoles = [ { id: 1, name: "Admin" }, { id: 2, name: "Manager" }, { id: 3, name: "Viewer" } ];
  }

  openAddUserModal() {
    this.isEditMode = false;
    this.editUserData = { username: '', email: '', userPassword: '', roleIds: [] };
    this.currentEditUserId = null;
    this.userModalOpen = true;
  }

  openEditUserModal(user: User) {
    this.isEditMode = true;
    this.editUserData = {
      username: user.username || '',
      email: user.email || '',
      userPassword: '', // Optional: let user enter new password or leave as is
      // Map from user.allRoles to an array of roleIds:
      roleIds: Array.isArray(user.allRoles) ? user.allRoles.map(role => role.roleId) : []
    };
    this.currentEditUserId = user.userId;
    this.userModalOpen = true;
  }

  closeUserModal() {
    this.userModalOpen = false;
    this.editUserData = { username: '', email: '', userPassword: '', roleIds: [] };
    this.currentEditUserId = null;
  }

  saveUser() {
    // Validate all fields (you can add more robust checks as needed)
    if (!this.editUserData.username || !this.editUserData.email || !this.editUserData.roleIds.length) {
      alert('Please fill all required fields.');
      return;
    }
    if (this.isEditMode && this.currentEditUserId != null) {
      // Edit user: do NOT include userId in DTO if your backend uses path param or session, just use DTO with values
      this.userService.updateAUser(this.editUserData).subscribe({
        next: () => {
          this.loadUsers();
          this.closeUserModal();
        },
        error: (err) => alert('Failed to update user: ' + err)
      });
    } else {
      if (!this.editUserData.userPassword) {
        alert('Password is required for new user.');
        return;
      }
      this.userService.addAUser(this.editUserData).subscribe({
        next: () => {
          this.loadUsers();
          this.closeUserModal();
        },
        error: (err) => alert('Failed to add user: ' + err)
      });
    }
  }

  deleteUser(userId: number) {
    if (confirm('Are you sure you want to delete this user?')) {
      this.userService.deleteAUser(userId).subscribe({
        next: () => this.loadUsers(),
        error: (err) => alert('Failed to delete user: ' + err)
      });
    }
  }

  // Checkbox logic for role selection in modal
  toggleRole(roleId: number) {
    const idx = this.editUserData.roleIds.indexOf(roleId);
    if (idx === -1) {
      this.editUserData.roleIds.push(roleId);
    } else {
      this.editUserData.roleIds.splice(idx, 1);
    }
  }

  isRoleSelected(roleId: number): boolean {
    return this.editUserData.roleIds.includes(roleId);
  }
}
