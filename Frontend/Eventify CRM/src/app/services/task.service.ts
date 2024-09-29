import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Task } from 'app/models/task.model';
import { Event } from 'app/models/event.model'; // Adjust the path as needed

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private baseUrl = 'http://localhost:8082/api/tasks'; // Adjust the URL as needed

  constructor(private http: HttpClient) {}

  getTasks(): Observable<Task[]> {
    return this.http.get<Task[]>(this.baseUrl);
  }

  getTaskById(taskId: number): Observable<Task> {
    return this.http.get<Task>(`${this.baseUrl}/${taskId}`);
  }

  getTasksByEvent(eventId: number): Observable<Task[]> {
    return this.http.get<Task[]>(`${this.baseUrl}/event/${eventId}`);
  }

  createTask(task: Task): Observable<Task> {
    return this.http.post<Task>(this.baseUrl, task);
  }
  

  updateTask(id: number, task: Task): Observable<Task> {
    return this.http.put<Task>(`${this.baseUrl}/${id}`, task);
  }

  deleteTask(taskId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${taskId}`);

  }

  getEvents(): Observable<Event[]> {
    return this.http.get<Event[]>('http://localhost:8082/api/events'); // Adjust the URL as needed
  }
}
