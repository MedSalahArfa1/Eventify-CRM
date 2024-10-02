import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Feedback } from 'app/models/feedback.model';
import { Event } from 'app/models/event.model';
import { FeedbackService } from 'app/services/feedback.service';
import { TaskService } from 'app/services/task.service'; // If you need events from tasks service

@Component({
  selector: 'app-feedbacks',
  templateUrl: './feedbacks.component.html',
  styleUrls: ['./feedbacks.component.css']
})
export class FeedbacksComponent implements OnInit {
  feedbacks: Feedback[] = [];
  filteredFeedbacks: Feedback[] = [];
  events: Event[] = [];
  currentFeedback: Feedback | null = null;
  isAddingFeedback: boolean = false;
  searchQuery: string = '';

  constructor(
    private feedbackService: FeedbackService,
    private tasksService: TaskService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadFeedbacks();
    this.loadEvents();
  }

  loadFeedbacks(): void {
    this.feedbackService.getAllFeedbacks().subscribe(feedbacks => {
      this.feedbacks = feedbacks;
      this.filterFeedbacks(); 
    });
  }

  loadEvents(): void {
    this.tasksService.getEvents().subscribe(events => {
      this.events = events;
    });
  }

  filterFeedbacks(): void {
    if (!this.searchQuery.trim()) {
      this.filteredFeedbacks = this.feedbacks;
    } else {
      const query = this.searchQuery.toLowerCase();
      this.filteredFeedbacks = this.feedbacks.filter(feedback => {
        const eventName = this.getEventName(feedback.eventId);
        return eventName.toLowerCase().includes(query);
      });
    }
  }

  getEventName(eventId: number): string {
    const event = this.events.find(event => event.eventId === eventId);
    return event ? event.eventName : 'Unknown Event';
  }

 

  

 

  deleteFeedback(feedbackId: number | null): void {
    if (confirm("Are you sure you want to delete this feedback?")) {
      this.feedbackService.deleteFeedback(feedbackId).subscribe(() => {
        this.loadFeedbacks();  
      }, error => {
        console.error('Error deleting feedback:', error);
      });
    }
  }

  resetForm(): void {
    this.currentFeedback = null;
    this.isAddingFeedback = false;
  }
}
