import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user.model';
import { UserDTO } from '../model/userdto.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class Userservice {

   baseUrl: string = `${environment.apiUrl}/user`;
  constructor(private httpClient : HttpClient){}

  getAllUsers(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.baseUrl);
  }

  getAUser(userId : number) : Observable<User>{
    return this.httpClient.get<User>(`${this.baseUrl}/${userId}`);
  }
  addAUser(newUser : UserDTO) : Observable<UserDTO>{
    return this.httpClient.post<UserDTO>(`${this.baseUrl}/add`, newUser);
  } 

  updateAUser(editUser: UserDTO) : Observable<UserDTO>{
    return this.httpClient.put<UserDTO>(`${this.baseUrl}/update`, editUser);
  }

  deleteAUser(userId: number): Observable<void>{
    return this.httpClient.delete<void>(`${this.baseUrl}/${userId}`);
  }
  getUsersByTeamId(teamId: number): Observable<User[]> {
    console.log(teamId);
  return this.httpClient.get<User[]>(`${this.baseUrl}/team/${teamId}`);
}


  

  
}
