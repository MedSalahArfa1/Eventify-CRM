import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Feedback } from 'app/models/feedback.model';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {
  private baseUrl = `http://localhost:8082/api/feedbacks`;

  constructor(private http: HttpClient) {}

  // Method to retrieve all feedbacks
  getAllFeedbacks(): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(this.baseUrl);
  }

  // Method to retrieve a feedback by ID
  getFeedbackById(feedbackId: number): Observable<Feedback> {
    return this.http.get<Feedback>(`${this.baseUrl}/${feedbackId}`);
  }

  // Method to create a new feedback
  addFeedback(feedbackData: { rating: number; comments: string; event: { eventId: number }; leftBy: any }) {
    return this.http.post<Feedback>(`${this.baseUrl}`, feedbackData);
}


  // Method to update an existing feedback
  updateFeedback(feedbackId: number, feedback: Feedback): Observable<Feedback> {
    return this.http.put<Feedback>(`${this.baseUrl}/${feedbackId}`, feedback);
  }

  // Method to delete a feedback by ID
  deleteFeedback(feedbackId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${feedbackId}`);
  }
}
