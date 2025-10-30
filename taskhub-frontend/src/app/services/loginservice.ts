import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserToken } from '../model/usertoken.model';
import { Observable } from 'rxjs';
import { User } from '../model/user.model';
import { UserDTO } from '../model/userdto.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class Loginservice {

  constructor(private httpClient: HttpClient){}

  baseUrl : string = `${environment.apiUrl}`;

  validateUser(user : any) : Observable<UserToken> {
    console.log(user);
    return this.httpClient.post<UserToken>(
      `${this.baseUrl}/user/validate`,
      user,
      {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
        })
      }
    )
  }
  
}
