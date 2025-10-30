import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Team } from '../model/team.model';
import { Observable } from 'rxjs';
import { User } from '../model/user.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class Teamservice {
  baseUrl: string = `${environment.apiUrl}/team`;
  constructor(private httpClient : HttpClient){}

  getAllTeams(): Observable<Team[]> {
    return this.httpClient.get<Team[]>(this.baseUrl);
  }

  getATeam(teamId : number): Observable<Team>{
    return this.httpClient.get<Team>(`${this.baseUrl}/${teamId}`);
  }

  addATeam(newTeam : Team): Observable<Team>{
    return this.httpClient.post<Team>(`${this.baseUrl}/add`, newTeam);
  }

  deleteATeam(teamId: number): Observable<void>{
    return this.httpClient.delete<void>(`${this.baseUrl}/${teamId}`)
  }

  
  updateATeam(editTeam: Team ): Observable<Team>{
    return this.httpClient.put<Team>(`${this.baseUrl}/update`, editTeam);
  }

  getUsersByTeamId(teamId: number): Observable<User[]> {
  return this.httpClient.get<User[]>(`${this.baseUrl}/team/${teamId}`);
}
}
