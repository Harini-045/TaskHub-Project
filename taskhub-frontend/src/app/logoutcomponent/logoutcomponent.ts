import { Component } from '@angular/core';
import { Authservice } from '../services/authservice';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logoutcomponent',
  imports: [],
  templateUrl: './logoutcomponent.html',
  styleUrl: './logoutcomponent.css',
})
export class Logoutcomponent {

  constructor(private authService: Authservice, private router: Router){}

  ngOnInit(){
    this.authService.deleteToken();
    this.authService.deleteUserDetails();
    this.authService.isLoggedIn = false;
    this.router.navigate(['login'])
    alert('Successful logout')
  }
}
