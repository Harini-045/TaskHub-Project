import { Injectable } from '@angular/core';
import { User } from '../model/user.model';
import { Role } from '../model/role.model';

@Injectable({
  providedIn: 'root'
})
export class Authservice {
  
  isLoggedIn : boolean = false;

  storeToken(token : string) : void {
    localStorage.setItem('jwtToken', token);
  }

  deleteToken() : void {
    localStorage.removeItem('jwtToken');
  }

  retrieveToken() : string | null {
    return localStorage.getItem('jwtToken');
  }

  storeUserDetails(user : User) : void {
    localStorage.setItem('userDetails', JSON.stringify(user));
  }

  deleteUserDetails() : void {
    localStorage.removeItem('userDetails');
  }

  retrieveUser() : User | null {
    let fetchedUser = localStorage.getItem('userDetails');
    if(fetchedUser != null){
      return JSON.parse(fetchedUser);
    }
    return null;
  }

  retrieveRole() : Role[] | undefined{
    let user = this.retrieveUser();
    return user?.allRoles;
  }

  isAdmin() : boolean {
    let user = this.retrieveUser();
    if(user?.allRoles.find(obj => obj.roleName === 'Admin')){
      return true;
    }
    return false;
  } 

  isManager() : boolean {
     let user = this.retrieveUser();
    if(user?.allRoles.find(obj => obj.roleName === 'Manager')){
      return true;
    }
    return false;
  }

  
  isDeveloper() : boolean {
     let user = this.retrieveUser();
    if(user?.allRoles.find(obj => obj.roleName === 'Developer')){
      return true;
    }
    return false;
  }
}
