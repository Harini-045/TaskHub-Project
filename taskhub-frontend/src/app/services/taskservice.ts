import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, ObservedValueOf } from 'rxjs';
import { Task } from '../model/task.model';
import { TaskDTO } from '../model/taskdto.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class Taskservice {

  baseUrl: string = `${environment.apiUrl}/task`;
  constructor(private httpClient : HttpClient){}

  getAllTasks(): Observable<Task[]> {
    return this.httpClient.get<Task[]>(this.baseUrl);
  }

  getATask(taskId : number): Observable<Task>{
    return this.httpClient.get<Task>(`${this.baseUrl}/${taskId}`);
  }

  getAUserTask(userId : number): Observable<Task[]>{
    return this.httpClient.get<Task[]>(`${this.baseUrl}/user/${userId}`);
  }

  addATask(newTask : Task): Observable<Task>{
    return this.httpClient.post<Task>(`${this.baseUrl}/add`, newTask);
  }

  updateATask(editTask: TaskDTO): Observable<TaskDTO>{
    return this.httpClient.put<TaskDTO>(`${this.baseUrl}/update`, editTask);
  }

  deleteATask(taskId: number): Observable<void>{
    return this.httpClient.delete<void>(`${this.baseUrl}/${taskId}`)
  }
  
}
