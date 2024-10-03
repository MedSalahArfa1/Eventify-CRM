import { Component, OnInit } from '@angular/core';
import { EventService } from 'app/services/event.service';
import { Event, EventImage } from 'app/models/event.model'; 
import { Router } from '@angular/router';
import { AuthService } from 'app/services/auth.service';

@Component({
  selector: 'app-explore',
  templateUrl: './explore.component.html',
  styleUrls: ['./explore.component.css']
})
export class ExploreComponent implements OnInit {
  events: Event[] = [];
  filteredEvents: Event[] = [];
  searchQuery: string = '';
  eventImages: { [key: number]: string } = {}; // Store images for events
  showFeedbackPopup = false;
  selectedEventId: number | null = null;

  constructor(private eventService: EventService, private router: Router, private authService: AuthService) {}

  ngOnInit(): void {
    this.loadEvents();
  }

  loadEvents(): void {
    this.eventService.getEvents().subscribe(events => {
      this.events = events.sort((a, b) => new Date(b.startDateTime!).getTime() - new Date(a.startDateTime!).getTime()); // Sort events by start date
      this.filteredEvents = this.events; // Initially display all events
      this.loadEventImages(); // Load images after events are loaded
    });
  }

  filterEvents(): void {
    if (!this.searchQuery.trim()) {
      this.filteredEvents = this.events; // Reset the filter if search query is empty
    } else {
      const query = this.searchQuery.toLowerCase();
      this.filteredEvents = this.events.filter(event =>
        event.eventName.toLowerCase().includes(query)
      );
    }
  }

  loadEventImages(): void {
    this.filteredEvents.forEach(event => {
      this.eventService.getEventImage(event.eventId!).subscribe((image: EventImage) => {
        // Convert byte array to Base64 string
        const base64String = btoa(String.fromCharCode.apply(null, image.picByte)); 
        this.eventImages[event.eventId!] = `data:image/jpeg;base64,${base64String}`;
      });
    });
  }

  getImageUrl(eventId: number): string {
    return this.eventImages[eventId]; // Return the image URL from the loaded images
  }

  openFeedbackPopup(eventId: number) {
    if (this.authService.isAuthenticated()) {
      this.selectedEventId = eventId;
      this.showFeedbackPopup = true;
    } else {
      this.router.navigate(['/login']);
    }
  }

  closeFeedbackPopup() {
    this.showFeedbackPopup = false;
    this.selectedEventId = null;
  }

  handleFeedbackAdded() {
    this.loadEvents(); // Refresh the events list or handle UI updates as needed
  }
}
