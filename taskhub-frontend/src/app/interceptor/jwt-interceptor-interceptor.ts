import { HttpInterceptorFn } from '@angular/common/http';
import { Authservice } from '../services/authservice';
import { inject } from '@angular/core';

export const jwtInterceptorInterceptor: HttpInterceptorFn = (req, next) => {
  const authService : Authservice = inject(Authservice);
  let token = authService.retrieveToken();

  if(token){
    const authRequest = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
    return next(authRequest);
  }
  return next(req);
};
