import { Injectable } from '@angular/core';
import { Label } from '../model/label.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Task } from '../model/task.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class Labelservice {

 
 baseUrl: string = `${environment.apiUrl}/label`;
  constructor(private httpClient : HttpClient){}

  getAllLabels(): Observable<Label[]> {
    return this.httpClient.get<Label[]>(this.baseUrl);
  }
  
}
