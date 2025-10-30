import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Role } from '../model/role.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class Roleservice {

   baseUrl: string = `${environment.apiUrl}/roles`;
  constructor(private httpClient : HttpClient){}

  getAllRoles(): Observable<Role[]> {
    return this.httpClient.get<Role[]>(this.baseUrl);
  }
  
}
