import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Event, EventImage } from 'app/models/event.model';

@Injectable({
  providedIn: 'root'
})
export class EventService {
  private apiUrl = 'http://localhost:8082/api/events';
  private imageUrl = 'http://localhost:8082/image'; // Set the base URL for image-related operations

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

  // Upload event image
  uploadEventImage(file: File, eventId: number): Observable<void> {
    const formData: FormData = new FormData();
    formData.append('eventFile', file); // Ensure the form data key matches backend parameter name
    return this.http.post<void>(`${this.imageUrl}/upload/event/${eventId}`, formData);
  }

  getEventImage(eventId: number): Observable<EventImage> {
    return this.http.get<EventImage>(`${this.imageUrl}/event/${eventId}`);
}

  // Delete event image
  deleteEventImage(eventId: number): Observable<void> {
    return this.http.delete<void>(`${this.imageUrl}/event/${eventId}`);
  }

  // Method to upload an event with an image (for combined upload)
  uploadEventWithImage(event: Event, file: File): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('eventFile', file);
    formData.append('event', new Blob([JSON.stringify(event)], { type: 'application/json' }));
    return this.http.post<any>(`${this.apiUrl}/upload`, formData);
  }
}
