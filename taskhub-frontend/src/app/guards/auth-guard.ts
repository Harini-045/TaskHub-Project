import { CanActivateFn, Router } from '@angular/router';
import { Authservice } from '../services/authservice';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  const authService: Authservice = inject(Authservice);
  const router : Router = inject(Router);
  if(authService.isLoggedIn){
    return true;
  }
  else{
    router.navigate(['login']);
    return false;
  }
};
