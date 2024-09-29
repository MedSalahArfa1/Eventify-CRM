import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Event } from 'app/models/event.model'; // Adjust the path according to your folder structure

@Injectable({
  providedIn: 'root'
})
export class EventService {
  private apiUrl = 'http://localhost:8082/api/events'; // Replace with your backend API URL

  constructor(private http: HttpClient) { }

  getEvents(): Observable<Event[]> {
    return this.http.get<Event[]>(this.apiUrl);
  }

  createEvent(event: Event): Observable<Event> {
    return this.http.post<Event>(this.apiUrl, event);
  }

  updateEvent(event: Event): Observable<Event> {
    return this.http.put<Event>(`${this.apiUrl}/${event.eventId}`, event);
  }

  deleteEvent(eventId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${eventId}`);
  }

  // New method to upload event image
  uploadEventImage(file: File, eventId: number): Observable<void> {
    const formData: FormData = new FormData();
    formData.append('file', file);

    return this.http.post<void>(`${this.apiUrl}/${eventId}/image`, formData);
  }

  // Method to get event image
  getEventImage(eventId: number): Observable<Blob> {
    return this.http.get(`${this.apiUrl}/${eventId}/image`, { responseType: 'blob' });
  }

  // Method to delete event image
  deleteEventImage(eventId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${eventId}/image`);
  }

  uploadEventWithImage(formData: FormData): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/upload`, formData);
  }
}
