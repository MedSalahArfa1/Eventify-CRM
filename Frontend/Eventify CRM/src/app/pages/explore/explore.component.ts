// explore.component.ts
import { Component, OnInit } from '@angular/core';
import { EventService } from 'app/services/event.service'; // Adjust the import path as necessary
import { Event } from 'app/models/event.model'; // Adjust the import path as necessary

@Component({
  selector: 'app-explore',
  templateUrl: './explore.component.html',
  styleUrls: ['./explore.component.css']
})
export class ExploreComponent implements OnInit {
  events: Event[] = [];
  filteredEvents: Event[] = [];
  searchQuery: string = '';

  constructor(private eventService: EventService) {}

  ngOnInit(): void {
    this.loadEvents();
  }

  loadEvents(): void {
    this.eventService.getEvents().subscribe(events => {
      this.events = events;
      this.filteredEvents = events; // Initially display all events
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
}
