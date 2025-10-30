import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { Loginservice } from '../services/loginservice';
import { Authservice } from '../services/authservice';
import { User } from '../model/user.model';

@Component({
  selector: 'app-login',
  imports: [FormsModule, RouterModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

  constructor(private router: Router, private loginService : Loginservice, private authService : Authservice) {}

  errorMessage: string = '';
  loginUser : User = {
    userId: 0,
    username: '',
    email: '',
    userPassword: '',
    allRoles: [],
    createdAt: new Date(),
    updatedAt: new Date()
  };

  handleFormSubmit(){
    console.log(this.loginUser);

this.loginService.validateUser(this.loginUser).subscribe({
next: (response) => {
this.authService.storeToken(response.token);
this.authService.storeUserDetails (response.user);
this.authService.isLoggedIn = true;
console.log(response.user);
if(this.authService.isAdmin()){

this.router.navigate(['/admin-task']);

} if(this.authService.isDeveloper()){

this.router.navigate(['/user-task']);
}

if(this.authService.isManager()){

this.router.navigate(['dashboard']);

}
alert('Successful Login')

},
error: (err) => {

console.log(err);


this.errorMessage = err;

this.router.navigate(['login']);
},
});
  }

}
